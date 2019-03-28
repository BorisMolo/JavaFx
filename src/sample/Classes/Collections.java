package sample.Classes;

import javafx.scene.layout.Pane;
import sample.Classes.Rabbits.AlbinosRabbit;
import sample.Classes.Rabbits.OdinaryRabbit;
import sample.Classes.Rabbits.Rabbit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class Collections {
    private ArrayList<Rabbit> arrayList;            //Коллекция для хранения объектов
    private TreeSet<Integer> treeSet;               //Коллекция для хранения и поиска уникальных идентификаторов
    private HashMap<Integer,Integer> hashMap;       //Коллекция для хранения времени рождения объектов

    public Collections(){
        arrayList = new ArrayList<Rabbit>();
        treeSet = new TreeSet<Integer>();
        hashMap = new HashMap<Integer,Integer>();
    }

    public void add(Rabbit rabbit){
        arrayList.add(rabbit);
        treeSet.add(rabbit.getIdentifier());
        hashMap.put(rabbit.getIdentifier(),rabbit.getTimeBorn());
        /*if (object instanceof OdinaryRabbit){
            addOdinaryRabbit((OdinaryRabbit)object);
        }

        if (object instanceof AlbinosRabbit){
            addAlbinosRabbit((AlbinosRabbit)object);
        }*/
    }

    private void addOdinaryRabbit(OdinaryRabbit odinaryRabbit){
        arrayList.add(odinaryRabbit);
        treeSet.add(odinaryRabbit.getIdentifier());
        hashMap.put(odinaryRabbit.getIdentifier(),odinaryRabbit.getTimeBorn());
    }

    private void addAlbinosRabbit(AlbinosRabbit albinosRabbit){
        arrayList.add(albinosRabbit);
        treeSet.add(albinosRabbit.getIdentifier());
        hashMap.put(albinosRabbit.getIdentifier(),albinosRabbit.getTimeBorn());
    }

    public void delete(Rabbit rabbit){
        arrayList.remove(rabbit);
        treeSet.remove(rabbit.getIdentifier());
        hashMap.remove(rabbit.getIdentifier(),rabbit.getTimeBorn());

        /*if (object instanceof OdinaryRabbit){
            deleteOdinaryRabbit((OdinaryRabbit)object);
        }

        if (object instanceof AlbinosRabbit){
            deleteAlbinosRabbit((AlbinosRabbit)object);
        }*/
    }

    private void deleteOdinaryRabbit(OdinaryRabbit odinaryRabbit){
        arrayList.remove(odinaryRabbit);
        treeSet.remove(odinaryRabbit.getIdentifier());
        hashMap.remove(odinaryRabbit.getIdentifier(),odinaryRabbit.getTimeBorn());
    }

    private void deleteAlbinosRabbit(AlbinosRabbit albinosRabbit){
        arrayList.add(albinosRabbit);
        treeSet.add(albinosRabbit.getIdentifier());
        hashMap.put(albinosRabbit.getIdentifier(),albinosRabbit.getTimeBorn());
    }

    public synchronized void  updateCollectionsPerTime(Pane pane){
        Iterator<Rabbit> iterator = arrayList.iterator();
        System.out.println("Array size is : "+arrayList.size());
        while (iterator.hasNext()) {
            Rabbit element = iterator.next();
            element.updaTimeLiveRabbit();
            if (element.isDead()){
                pane.getChildren().remove(element.getImageView());
                delete(element);
            }
            /*Object element = iterator.next();
            if (element instanceof OdinaryRabbit){
                OdinaryRabbit newElement = (OdinaryRabbit)element;
                newElement.updaTimeLiveRabbit();
                if (newElement.isDead()){
                    pane.getChildren().remove(newElement.getImageView());
                    delete(newElement);
                }
            }
            if (element instanceof AlbinosRabbit){
                AlbinosRabbit newElement = (AlbinosRabbit)element;
                newElement.updaTimeLiveRabbit();
                if (newElement.isDead()) {
                    continue;
                }
                pane.getChildren().remove(newElement.getImageView());
                delete(newElement);
            }*/


        }
    }


}
