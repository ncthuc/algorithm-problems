package land.study;

/**
 * Created by Thuc on 5/25/2016.
 * CONG_TRU_1
 */
public class CongTru1 {
    private int[] seeds = {27, 9, 3, 1, 0};

    public void solve() {
        for (int v = 40; v >=1; v--) {
            String s = f(v, 0);
            if (!s.endsWith("#")) System.out.println(v + " = " +  s.substring(3));
        }
    }

    private String f(int v, int i) {
        if (i >= seeds.length) return "#";

        if (v==0) return "";

        if (v == seeds[i]) return " + " + seeds[i];

        String cong = f(v-seeds[i], i + 1);
        if (!cong.endsWith("#")) return " + " + seeds[i] + cong;

        String tru = f(v+seeds[i], i + 1);
        if (!tru.endsWith("#")) return " - " + seeds[i] + tru;

        return f(v, i+1);
    }
}
