package main.java;

import main.java.utils.ExcepcionDynamicList;
import main.java.utils.Orderable;

/**
 * <p>
 * Clase que permite crear una lista que almacena nodos de tipo DoublyLink y
 * ofrece métodos para realizar operaciones sobre la lista doblemente ligada
 * </p>
 * 
 * @author tonycituk
 */
public class DoublyLinkList<T extends Orderable> {
    private DoublyLink<T> first;

    public DoublyLinkList() {
        first = null;
    }

    /**
     * <p>
     * Regresa el nodo correspondiente a la primera posición de la
     * lista.
     * </p>
     * 
     * @return El primer nodo de la lista si es que no está vacía, null en caso
     *         contrario.
     * @throws ExcepcionDynamicList
     * 
     */
    public DoublyLink<T> getFirst() {
        return this.first;
    }

    /**
     * <p>
     * Regresa el nodo correspondiente a la última posición de la lista.
     * </p>
     * 
     * @return El último nodo de la lista si es que no está vacía, null en caso
     *         contrario.
     * 
     */
    public DoublyLink<T> getLast() {
        if (this.isEmpty()) {
            return first; // null
        } else {
            DoublyLink<T> temp = first;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            return temp;
        }
    }

    /**
     * <p>
     * Retorna el número de elementos que contiene la lista.
     * </p>
     * 
     * @return Un entero que representa la cantidad de elementos almacenados en la
     *         lista.
     * 
     */
    public int getSize() {
        int size = 0;
        DoublyLink<T> temp = first;
        while (temp != null) {
            temp = temp.getNext();
            size++;
        }
        return size;
    }

    // Método para insertar un elemento antes de uno proporcionado (búsqueda por
    // dato).
    /**
     * <p>
     * Este método permite insertar un nodo nuevo antes de algún nodo existente con
     * el elemento proporcionado.
     * </p>
     * <p>
     * Es importante crear tu propio método toString en la clase a usar ya que está
     * funcion hace uso de ese método.
     * </p>
     * 
     * @param objRef El dato existente en alguno de los nodos de la lista, que
     *               servirá de referencia para insertar antes.
     * @param objNew El nuevo dato que se asignará a un nuevo nodo que será
     *               insertado antes del nodo de referencia.
     * 
     */
    public void insertBefore(T objRef, T objNew) throws ExcepcionDynamicList {
        DoublyLink<T> temp = first;
        while (temp != null) {
            if (objRef.toString().equals(temp.getDato().toString())) {
                DoublyLink<T> newLink = new DoublyLink<T>(objNew);
                newLink.setNext(temp);
                newLink.setPrev(temp.getPrev());
                temp.setPrev(newLink);

                if (newLink.getPrev() != null) {
                    newLink.getPrev().setNext(newLink);
                }
                break;
            }
            temp = temp.getNext();
        }
        if (temp == null) {
            throw new ExcepcionDynamicList("Error : no se encontró el elemento de referencia para la inserción ");
        }
    }

    // Método para insertar un elemento después de uno proporcionado (búsqueda por
    // dato).
    /**
     * <p>
     * Este método permite insertar un nodo nuevo después de algún nodo existente.
     * </p>
     * <p>
     * Es importante crear tu propio método toString en la clase a usar ya que está
     * funcion hace uso de ese método.
     * </p>
     * 
     * 
     * @param objRef El dato existente en alguno de los nodos de la lista, que
     *               servirá de referencia para insertar después de él.
     * @param objNew El nuevo dato que se asignará a un nuevo nodo que será
     *               insertado después del nodo de referencia.
     * 
     */
    public void insertAfter(T objRef, T objNew) throws ExcepcionDynamicList {
        DoublyLink<T> temp = first;
        while (temp != null) {
            if (objRef.toString().equals(temp.getDato().toString())) {
                DoublyLink<T> newLink = new DoublyLink<T>(objNew);
                if (temp.getNext() != null) {
                    temp.getNext().setPrev(newLink);
                }
                newLink.setNext(temp.getNext());
                temp.setNext(newLink);
                newLink.setPrev(temp);
                break;
            }
            temp = temp.getNext();
        }
        if (temp == null) {
            throw new ExcepcionDynamicList("Error : no se encontró el elemento de referencia para la inserción ");
        }
    }

    // Método para insertar elemento en forma ordenada a.
    /**
     * <p>
     * Este método permite insertar un nodo de manera ordenada y creciente, es decir
     * agrega un nuevo nodo antes de algún nodo que resulte ser mayor que el
     * primero.
     * </p>
     * 
     * @param dato El dato que se asignará al nuevo nodo y será colocado en una
     *             posición ordenada en la lista.
     * 
     */
    public void insertCrescent(T dato) {
        DoublyLink<T> aux = first;

        DoublyLink<T> newLink = new DoublyLink<T>(dato);
        if (isEmpty()) {
            first = newLink;
            return;
        }

        while (aux.getValor() <= newLink.getValor() && aux.getNext() != null) {
            aux = aux.getNext();
        }

        if (aux.getNext() != null) {
            aux.getNext().setPrev(newLink);
        }
        newLink.setNext(aux.getNext());
        aux.setNext(newLink);
        newLink.setPrev(aux);

    }

    // Método para insertar elemento en forma ordenada b. Decreciente
    /**
     * <p>
     * Este método permite insertar un nodo de manera ordenada y decreciente, es
     * decir agrega un nuevo nodo antes de algún nodo que resulte ser menor que el
     * primero.
     * </p>
     * 
     * @param dato El dato que se asignará al nuevo nodo y será colocado en una
     *             posición ordenada en la lista.
     * 
     */
    public void insertDecrescent(T dato) {
        DoublyLink<T> aux = first;

        DoublyLink<T> newLink = new DoublyLink<T>(dato);
        if (isEmpty()) {
            first = newLink;
            return;
        }

        while (aux.getValor() >= newLink.getValor() && aux.getNext() != null) {
            aux = aux.getNext();
        }

        if (aux.getNext() != null) {
            aux.getNext().setPrev(newLink);
        }
        newLink.setNext(aux.getNext());
        aux.setNext(newLink);
        newLink.setPrev(aux);
    }

    /**
     * <p>
     * Este método permite insertar un nodo de manera ordenada; ya sea creciente o
     * decreciente.
     * </p>
     * 
     * <p>
     * Usar 0 para insertar creciente.
     * </p>
     * <p>
     * Usar un número distinto de 0 para insertar decreciente.
     * </p>
     * 
     * 
     * @param dato El dato que se asignará al nuevo nodo y será colocado en una
     *             posición ordenada en la lista.
     * 
     */
    public void insertInOrder(T dato, int order) {
        if (order == 0) {
            insertCrescent(dato);
        } else {
            insertDecrescent(dato);
        }

    }

    // Método para eliminar un elemento proporcionado (mediante su dato)
    /**
     * <p>
     * Este método permite eliminar el nodo de la lista que contenga un dato
     * específico.
     * </p>
     * 
     * @param data El dato existente en alguno de los nodos de la lista, que servirá
     *             para buscar el nodo a eliminar.
     * 
     */
    public void deleteWhere(T data) throws ExcepcionDynamicList {
        if (first.getDato().toString().equals(data.toString())) {
            deleteFirst();
        } else {
            DoublyLink<T> temp = first;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                if (temp.getDato().toString().equals(data.toString())) {
                    DoublyLink<T> newLink = new DoublyLink<T>(data);
                    newLink.setNext(temp.getNext());
                    newLink.setPrev(temp.getPrev());

                    if (newLink.getPrev() != null) {
                        newLink.getPrev().setNext(newLink);
                    }
                    if (temp.getNext() != null) {
                        temp.getNext().setPrev(newLink);
                    }
                    break;
                }
            }
        }
        throw new ExcepcionDynamicList("Error : no se encontró el elemento para la eliminación ");

    }

    // Método para eliminar un nodo de una posición proporcionada mediante su indice
    /**
     * <p>
     * Este método permite eliminar el nodo de la lista que corresponda a una
     * posición determinada
     * </p>
     * 
     * @param posDel El entero que representa la posición del nodo que se desea
     *               eliminar.
     * 
     */
    public void deleteAt(int posDel) throws ExcepcionDynamicList {
        if (posDel > 0) {
            boolean eliminado = false;
            if (posDel == 1) {
                try {
                    deleteFirst();
                    eliminado = true;
                } catch (ExcepcionDynamicList e) {
                    e.printStackTrace();
                }
            } else {
                DoublyLink<T> temp = first;
                int i = posDel;
                while (temp != null && i > 1) { // llega al nodo por eliminar
                    temp = temp.getNext();
                    i--;
                }
                if (temp != null && i == 1) {
                    if (temp.getNext() != null) {
                        temp.getNext().setPrev(temp.getPrev());
                    }
                    if (temp.getPrev() != null) {
                        temp.getPrev().setNext(temp.getNext());
                    }else{
                        first = temp.getNext();
                    }
                    eliminado = true;
                }

            }
            if (!eliminado) {
                throw new ExcepcionDynamicList("No se pudo eliminar en dicha posición");
            }
        } else {
            throw new ExcepcionDynamicList("No se pudo eliminar en dicha posición");
        }
    }

    // Método para eliminar todos los elementos de la lista (limpiar la lista).
    /**
     * <p>
     * Este método eliminar todos los elementos de la lista para dejarla vacía.
     * </p>
     */
    public void clearList() {
        // Elimina los nodos desde el primero hasta el último
        first = null;
    }

    // Método para hacer una búsqueda de un elemento y devolver -1 si no lo encontró
    // y la posición del dato en la lista en caso de que se haya encontrado
    /**
     * <p>
     * Este método permite buscar un dato para saber si se encuentra en alguno de
     * los nodos de la lista, también permite conocer la posición del dato si se
     * encontró.
     * </p>
     * 
     * @param data El dato que servirá de referencia para buscar si en la lista se
     *             encuentra algún nodo que contenga dicho dato.
     * @return Un entero que representa la posición del nodo donde se encontró el
     *         dato en la lista. Si regresa -1 indica que no se encontró un nodo con
     *         dicho dato
     */
    public int buscar(T data) {
        DoublyLink<T> temp = first;
        int i = 1; // registra la posición del elemento por buscar
        if (isEmpty()) {
            return -1;
        } else {
            while (temp != null) {
                if (temp.getDato().toString().equals(data.toString())) {
                    return i;
                }
                temp = temp.getNext();
                i++;
            }
            return -1;
        }
    }

    // Método para reemplazar un nodo de una posición proporcionada con otro nodo.
    /**
     * <p>
     * Este método permite reemplazar el nodo de una posición determinada en la
     * lista con un nuevo nodo.
     * </p>
     * 
     * @param pos El entero que representa la posición del nodo que se desea
     *            sustituir.
     * @param l   El nodo que se desea que sustituya al nodo de la posición enviada.
     * 
     */
    public void replaceAtWith(int pos, DoublyLink<T> doublyLink) throws ExcepcionDynamicList {
        if (pos > 0) {
            DoublyLink<T> temp = first;
            int i = pos;
            boolean reemplazo = false;
            while (temp != null && i > 1) { // llega antes del nodo por eliminar
                //tempAnterior = temp;
                temp = temp.getNext();
                i--;
            }
            if (temp != null) {
                doublyLink.setNext(temp.getNext());
                doublyLink.setPrev(temp.getPrev());

                if (doublyLink.getPrev() != null) {
                    doublyLink.getPrev().setNext(doublyLink);
                }
                if (temp.getNext() != null) {
                    temp.getNext().setPrev(doublyLink);
                }
                reemplazo = true;
            }
            if (!reemplazo) {
                throw new ExcepcionDynamicList("No se pudo reemplazar en dicha posición");
            }
        } else {
            throw new ExcepcionDynamicList("No se pudo reemplazar en dicha posición");
        }
    }

    /**
     * <p>
     * Este método permite verificar si la lista está vacía.
     * </p>
     * 
     * @return True en caso de que la lista esté vacía, false en caso contrario.
     * 
     */
    public boolean isEmpty() {
        return (first == null);
    }

    /**
     * <p>
     * Este método permite insertar un nodo nuevo en la primera posición de la
     * lista.
     * </p>
     * 
     * @param dd El dato que se requiere asignar al nuevo nodo de la lista.
     * 
     */
    public void insertFirst(T dd) {
        DoublyLink<T> newLink = new DoublyLink<T>(dd);
        newLink.setNext(first);
        first = newLink;
    }

    /**
     * <p>
     * Este método permite insertar un nodo nuevo para que ocupe la última posición
     * de la lista.
     * </p>
     * 
     * @param dd El dato que se requiere asignar al nuevo nodo de la lista.
     * 
     */
    public void insertLast(T dd) {
        if (isEmpty()) {
            insertFirst(dd);
        } else {
            DoublyLink<T> temp = first;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            DoublyLink<T> newLink = new DoublyLink<T>(dd);
            newLink.setPrev(temp);
            temp.setNext(newLink);
        }
    }

    /**
     * <p>
     * Este método permite eliminar un nodo desde la última posición de la lista.
     * </p>
     * 
     * @return El nodo que anteriormente a la eliminación ocupaba la última posición
     *         de la lista.
     * 
     */
    public DoublyLink<T> deleteLast() throws ExcepcionDynamicList {
        if (isEmpty()) {
            throw new ExcepcionDynamicList("Error : la lista está vacía");
        } else {
            DoublyLink<T> temp = first;
            // Caso donde el nodo por eliminar es el único
            if (temp.getNext() == null) {
                first = null;
                return temp;
            } else {
                // Caso donde hay dos o más elementos
                while (temp.getNext().getNext() != null) {
                    temp = temp.getNext();
                }
                DoublyLink<T> last = temp.getNext();
                temp.setNext(null);
                return last;
            }

        }
    }

    /**
     * <p>
     * Este método permite eliminar un nodo desde la primera posición de la lista.
     * </p>
     * 
     * @return El nodo que anteriormente a la eliminación ocupaba la primera
     *         posición de la lista.
     * 
     */
    public DoublyLink<T> deleteFirst() throws ExcepcionDynamicList {
        DoublyLink<T> temp = null;
        if (isEmpty()) {
            throw new ExcepcionDynamicList("Error : la lista está vacia");
        } else {
            temp = first;
            first = first.getNext();
            first.setPrev(null);
        }
        return temp;
    }

    /**
     * <p>
     * Este método permite imprimir todos los elementos que tiene almacenada la
     * lista.
     * </p>
     */
    public void displayList() throws ExcepcionDynamicList {
        if (isEmpty()) {
            throw new ExcepcionDynamicList("Error : la lista está vacia");
        }
        System.out.print("List (first--> ");
        DoublyLink<T> current = first;
        while (current != null) {
            current.displayLink();
            current = current.getNext();
        }
        System.out.println("<--last)");
    }

}