package list.linked;

public class MyLinkedListMain {

    public static void main(String[] args) {
        MyLinkedList<Integer> intList = new MyLinkedList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        Integer integer1 = intList.get(0);
        Integer integer2 = intList.get(1);
        Integer integer3 = intList.get(2);
        System.out.println("integer1 = " + integer1);
        System.out.println("integer2 = " + integer2);
        System.out.println("integer3 = " + integer3);
        System.out.println(intList + "\n");

        MyLinkedList<String> stringList = new MyLinkedList<>();
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");
        String string1 = stringList.get(0);
        String string2 = stringList.get(1);
        String string3 = stringList.get(2);
        System.out.println("string1 = " + string1);
        System.out.println("string2 = " + string2);
        System.out.println("string3 = " + string3);
        System.out.println(stringList);
    }
}
