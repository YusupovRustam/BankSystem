public class Checking {
    public static boolean checkPassword(String password) {
        String[] s = password.split("");
        int count = 0;
        int count1 = 0;
        int a = 0;
        if (password.length() != 5) {
            return false;
        }
        for (int i = 0; i < s.length; i++) {

            if (s[i].toLowerCase() != s[i]) {
                count++;
            }

            if (s[i].toUpperCase() != s[i]) {
                count1++;
            }
            try {
                a = Integer.parseInt(s[i]);
            } catch (Exception e) {
            }
        }
        if ((a > 0) && (count >= 1 && count < 5) && (count1 >= 1 && count1 < 5)) {
            return true;
        }
        return false;
    }
}
