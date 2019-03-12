package sample.Classes.Rabbits;


/*
* Создать интерфейс IBehaviour, задающий поведение объекта (методы: move(), getx(), gety(), sety(), setx()  и другие.
* Далее будут реализоваться алгоритмы движения объектов в окне программы).
 Реализовать иерархию классов, определяющих объекты по варианту и реализующие интерфейс IBehaviour.
*
* */
public interface IBehaviour {
    void move(int x, int y);
    void setX(int x);
    void setY(int y);
    double getX();
    double getY();
}
