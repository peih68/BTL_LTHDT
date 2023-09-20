import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TuDien 
{
    private Map<String, List<TongHop>> duLieu = new TreeMap<>();

    public void themTu(String tuKhoa, TongHop thongTinTu) {
        if (!duLieu.containsKey(tuKhoa)) {
            duLieu.put(tuKhoa, new ArrayList<TongHop>()); // Tạo một danh sách mới
        }
        duLieu.get(tuKhoa).add(thongTinTu);
    }

    public void xoa(String tuKhoa) {
        duLieu.remove(tuKhoa);
    }

    public List<TongHop> traTu(String tuKhoa) {
        return duLieu.get(tuKhoa);
    }

    public void print()
    {

    }
}
