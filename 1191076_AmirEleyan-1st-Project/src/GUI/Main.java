package GUI;

import linkedList.LinkedList;
import linkedList.Node;
import linkedList.TRecord;

public class Main {

    public static void main(String[] args) {
        LinkedList<TRecord> list = new LinkedList<>();

//        list.insert(new TRecord("33090305,Scientific ,86.2"));
//        list.insert(new TRecord("33090304,Scientific ,86.6"));
//        list.insert(new TRecord("33090303,Scientific ,67.1"));
//        list.insert(new TRecord("33090209,Literary ,66"));
//        list.insert(new TRecord("33090208,Literary ,78.3"));
//        list.insert(new TRecord("33090207,Literary ,66.6"));
//        list.insert(new TRecord("29082127,Scientific,69.5"));
//        list.insert(new TRecord("29082128,Scientific,81.1"));
//        list.insert(new TRecord("29082129,Scientific,72.3"));
//        list.insert(new TRecord("29082132,Scientific,68.5"));
//        list.insert(new TRecord("29082133,Scientific,92.5"));
//        list.insert(new TRecord("29082135,Scientific,92.5"));
//        list.insert(new TRecord("33090103,Literary ,84"));
//        list.insert(new TRecord("33090106,Literary ,72.3"));
//        list.insert(new TRecord("33090201,Literary ,58.9"));
//        list.insert(new TRecord("33090202,Literary ,86.8"));
//        list.insert(new TRecord("33090203,Literary ,76.5"));
//        list.insert(new TRecord("33090204,Literary ,72.3"));
//        list.insert(new TRecord("33090205,Literary ,76.5"));
//        list.insert(new TRecord("33090206,Literary ,77.3"));
        //  System.out.println(Calculations.numberOfRecordAboveAGrade(list,70));
//
//        LinkedList<TRecord> top = new LinkedList<>();
//        Calculations.topTenGrads(list, top);
        //  list.printListReverse(list.getHead());


        /*LinkedList<Integer> tt = new LinkedList<>();
        tt.addFirst(8); // 7 9 10 12 8
        tt.addFirst(10);
        tt.addWithSpecificSort(9);
        tt.addWithSpecificSort(7);
        tt.addWithSpecificSort(12);
        System.out.println(tt.toString());*/

        LinkedList<Integer> toto = new LinkedList<>();

       System.out.println(toto.addBySort(5));
        System.out.println(toto.addBySort(6));
        System.out.println(toto.addBySort(6));
        System.out.println(toto.addBySort(null));
        toto.printList();


    }
}
