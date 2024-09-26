package Exercices;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * An empty list is an instance of class {@code Liste} where {@code head=queue=null}.
 * A list containing one element {@code v} is represented by 2 objects of {@code Liste} where :
 * <ul> 
 * <li>{@code v} is stored in {@code head} attribute of first object</li>
 * <li>queue attribute of first object refers to second {@code Liste} object where head=queue=null.</li>
 * </ul> 
 * @author Dr. Denis PALLEZ </br>
 * * @version 2017-2018
 */

public class ListeE<E> { //
    private E tete;
    private ListeE<E> queue;
    
    public ListeE() {
        tete = null;
        queue = null;
    }
    public ListeE(E n, ListeE<E> suivante) {
        tete=n ;
        queue = suivante ;
    }
    public E tete() {
        return tete;
    }
    public ListeE<E> queue() {
        return queue;
    }
    public boolean estVide() {
        return (tete==null) ;
    }
    
    public int taille() {
        if (estVide())
            return 0;
        else
            return 1 + queue.taille();
    }
    
    public E[] toArray() {
        ListeE<E> l=this ;
        E[] array = (E[])Array.newInstance(tete.getClass(),l.taille()) ;
        int i=0 ;
        while (!(l.estVide())) {
            array[i]=l.tete() ;
            l=l.queue() ;
            i++ ;
        }
        return array ;
    }


    //Question a

    public String toString(){
        if(!estVide()){
            return   tete.toString() + " --> " + queue.toString() ;
        }
        else{return "";}
    }


    //Question b
    public void AddTailProc(E element){
        if(estVide()){
            tete = element;
            queue = new ListeE<E>();
        }
        else{
            queue.AddTailProc(element);
        }

    }



public ListeE<E> addTail_Fonc(E e){
    // Si la liste est vide, créez une nouvelle liste avec l'élément comme seule tête.
    if(estVide()){
        return new ListeE<E>(e, new ListeE<E>());
    } else {
        // Sinon, recréez la tête actuelle et appelez addTail_Fonc récursivement sur la queue.
        // Cette appel construit une nouvelle liste qui contient tous les éléments précédents suivis du nouvel élément.
        return new ListeE<E>(tete, queue.addTail_Fonc(e));
    }
}







    //Question b
    public void AddHead_Proc(E element){
        E current_head = tete;
        if(estVide()){
            tete = element;
            queue = new ListeE<E>();
        }
        else{
            tete = element;
            queue = new ListeE<E>(current_head,queue);
        }

    }

    public ListeE<E> addHead_Fonc(E e){
        if(estVide()){
            ListeE<E> nul = new ListeE<E>();
            ListeE<E> copie = new ListeE<E>(e,nul);
            return copie;
        }else{
            ListeE<E> copiee = new ListeE<E>(e,addHead_Fonc(tete));
            return copiee;
        }
    }


    //Question d
    public ListeE(ListeE<E> liste_copier) { //Constructeur par copie
        this.queue = liste_copier.queue;
        this.tete = liste_copier.tete;
    }


    //Question e 
    
   
   
   
    public ListeE<E> delHeadFonc() {
        // Si la liste est vide, retournez simplement la liste vide
        if (this.estVide()) {
            return new ListeE<E>(); // ou `return this;` si vous voulez retourner l'instance vide actuelle
        } else if (this.queue.estVide()) {
            // Si la queue est vide, cela signifie qu'il n'y a qu'un seul élément dans la liste.
            // Retournez une nouvelle liste vide.
            return new ListeE<E>();
        } else {
            // Sinon, retournez une nouvelle liste qui commence par l'élément suivant dans la queue.
            return this.queue.delHeadFonc();
        }
    }
   
   
   
   
   
    public void delHeadProc() {
        // Si la liste est vide ou ne contient qu'un seul élément, il n'y a rien à faire.
        if (this.estVide() || this.queue.estVide()) {
            this.tete = null;
            this.queue = null;
        } else {
            // Sinon, définissez la tête actuelle sur la tête de la queue,
            // et la queue actuelle sur la queue de la queue.
            this.tete = this.queue.tete;
            this.queue = this.queue.queue;
        }
    }
   
   
   
   
   
     public ListeE<E> delTailFonc() {
        // Si la liste est vide ou ne contient qu'un seul élément, retournez une liste vide.
        if (this.estVide() || this.queue.estVide()) {
            return new ListeE<E>();
        } else {
            // Appel récursif pour reconstruire la liste sans le dernier élément.
            return new ListeE<E>(tete, queue.delTailFonc());
        }
    }
   
   
   
   
    public void delTail_Proc(){
        if(!estVide()){
            if(queue.estVide()){
                tete = null;
                queue = null;
            }
        else{
            queue.delTail_Proc();
        }}
    }










    public boolean equals(Object obj){
        if(!(obj instanceof ListeE<?>)){
            return false;
        }
        ListeE<E> liste = (ListeE<E>) obj;

        if(this.tete==liste.tete){
            queue.equals(liste);
            return true;
        }
        else{return false;}

    
    }

    public boolean contient(E e){
        if(estVide()){
            return false;
        }

        else if(this.tete==e){
            return true;
        }

        else{
            return queue.contient(e);
        }
    }

public void inverseProc() {
    ListeE<E> prev = null;
    ListeE<E> current = this;
    while (!current.estVide()) {
        ListeE<E> next = current.queue;
        current.queue = prev;
        prev = current;
        current = next;
    }
    this.tete = (prev != null) ? prev.tete : null;
    this.queue = (prev != null) ? prev.queue : null;
}


// question m 


public ListeE<E> ajouteTous(ListeE<E> autreListe) {
    if (this.estVide()) {
        return autreListe;
    } else {
        return new ListeE<>(tete, queue.ajouteTous(autreListe));
    }
}
//n

public void supprimeTousProc() {
    this.tete = null;
    this.queue = null;
}

public void supprimeTousNonRec() {
    while (!this.estVide()) {
        this.delHeadProc(); // Supposerait une méthode existante delHeadProc() qui supprime la tête de la liste
    }
}


    public static void main(String[] args) {

            //Question c
            int n = 10;
            ListeE<Integer> liste = new ListeE<Integer>();
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                liste.AddHead_Proc(random.nextInt());
            }
            //System.err.println(liste.toString());
            //System.out.println(liste.toString());
            


            //Question f
            int na = 5;
            ListeE<Integer> l_a = new ListeE<Integer>();
            Random random1 = new Random();
            for (int i = 0; i < na; i++) {
                l_a.AddHead_Proc(random1.nextInt());
            }

            System.err.println(l_a.toString());
            
            ListeE<Integer> l_c = new ListeE<>(l_a);
            l_c.delTail_Proc();
            
            System.err.println(l_a.toString());
            System.err.println(l_c.toString());
            
            //On remarque que après avoir fait la copie de la liste et qu'on modofie la liste copié, cela modifie aussi la liste de base
            
            System.out.println(l_a.equals(l_c)); //Renvoie false donc la definition equals est juste
            //Ajouter une queue en fonctionnel veut dire créer une autre liste par copie et ajoter la queue souhaité
        }
}
