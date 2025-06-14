package list;

public class MyArrayListMain {

    public static void main(String[] args) {
        MyArrayList<String> stringList = new MyArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        String string = stringList.get(0);
        System.out.println("string = " + string);

        MyArrayList<Integer> integerList = new MyArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        Integer integer = integerList.get(0);
        System.out.println("integer = " + integer);

        Integer removed1 = integerList.remove(2);
        System.out.println("remove(2)=" + removed1);
        System.out.println(integerList);

        Integer removed2 = integerList.remove(1);
        System.out.println("remove(1)=" + removed2);
        System.out.println(integerList);
    }
}
