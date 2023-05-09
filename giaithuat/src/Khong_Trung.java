import java.util.*;

public class Khong_Trung {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 1, 4, 2, 5};

        // Tạo một HashMap để lưu trữ số lần xuất hiện của mỗi giá trị
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (countMap.containsKey(arr[i])) {
                // Nếu giá trị đã xuất hiện, tăng số lần xuất hiện lên 1
                countMap.put(arr[i], countMap.get(arr[i]) + 1);
            } else {
                // Nếu giá trị chưa xuất hiện, thêm vào map với số lần xuất hiện là 1
                countMap.put(arr[i], 1);
            }
        }

        // Tìm các giá trị xuất hiện đúng 1 lần và lưu vào một ArrayList
        ArrayList<Integer> uniqueValues = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueValues.add(entry.getKey());
            }
        }

        System.out.println(uniqueValues);
    }
}

class Trung_2 {
    public static void main(String[] args) {
        String arr = "conggg ddddinh";
        Set<Character> arr2 = new HashSet<>();
        for (char c : arr.toCharArray()) {
            arr2.add(c);

        }
        System.out.println(" " + arr2);
        for (char c : arr2) {
            int count = 0;
            for (char b : arr.toCharArray()) {
                if (c == b) {
                    count++;
                }
            }
            System.out.println("'" + c + ":  " + count + " lần");
        }
    }
}

class Khong_Trung_3 {
    public static void main(String[] args) {
        int count = 0;
        String[] array = {"1", "1", "2", "2", "3", "4", "5", " ", "1"};
        List<String> arrayResult = new ArrayList<>();
        for (String value : array) {
            for (String s : array) {
                if (value.equals(s)) {
                    count += 1;
                }
            }
            if (count == 1) {
                arrayResult.add(value);
            }
            count = 0;
        }
        System.out.println(arrayResult);
//        String[] array = {"1", "1", "2", "2", "3", "4","5"," ","1"};
//        Map<String, Integer> map = new HashMap<>();
//        for (String value : array) {
//            map.put(value, map.getOrDefault(value, 0) + 1);
//        }
//        List<String> arrayResult = new ArrayList<>();
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            if (entry.getValue() == 1) {
//                arrayResult.add(entry.getKey());
//            }
//        }
//
//        System.out.println(arrayResult);
    }

}
