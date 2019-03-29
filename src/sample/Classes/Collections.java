package sample.Classes;

import javafx.scene.layout.Pane;
import sample.Classes.Rabbits.AlbinosRabbit;
import sample.Classes.Rabbits.OdinaryRabbit;
import sample.Classes.Rabbits.Rabbit;

import java.util.*;

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
    }

    public void delete(Rabbit rabbit){
        arrayList.remove(rabbit);
        treeSet.remove(rabbit.getIdentifier());
        hashMap.remove(rabbit.getIdentifier(),rabbit.getTimeBorn());
    }

    public  void  updateCollectionsPerTime(Pane pane){
        Iterator<Rabbit> iteratorUpdate = arrayList.iterator();
        while (iteratorUpdate.hasNext())
        {
            Rabbit rabbitUpdate = iteratorUpdate.next();
            rabbitUpdate.updaTimeLiveRabbit();
        }

        /*
         *       У меня так и не получилось обойти ошибку ConcurrentModificationException
         *       Возникает из-за того, что в потоке нельзя УДАЛЯТЬ элемент из КОЛЛЕКЦИИ
         *       Не смог найти альтернативу, кроме как разбить эту операцию на 2 разных итератора
         *       Каждый итератор завернут в функцию
         * */

        while(checkIsAmyRabbitDead()){
            Rabbit deleRabbit = findDeadRabbit();
            delete(deleRabbit);
            pane.getChildren().remove(deleRabbit.getImageView());
        }

    }

    private boolean checkIsAmyRabbitDead(){
        Iterator<Rabbit> iteratorDelete = arrayList.listIterator();
        while (iteratorDelete.hasNext()) {
            Rabbit element = iteratorDelete.next();
            if (element.isDead())
            {
                return true;
            }
        }
        return  false;
    }

    private Rabbit findDeadRabbit(){
        Iterator<Rabbit> iteratorDelete = arrayList.listIterator();
        while (iteratorDelete.hasNext()) {
            Rabbit element = iteratorDelete.next();
            if (element.isDead())
            {
                return element;
            }
        }
        return  null; // NEVER RETURN NULL!!
    }

    public void clear(){
        arrayList.clear();
        treeSet.clear();
        hashMap.clear();
    }

}
