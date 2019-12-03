import java.util.Iterator;


public class Pavlatos implements Iterable<Movie> {

    private int size = 0;
    private Movie first;
    private Movie last;
    private boolean isSorted = true;

    private Movie createMovie(String title, int year, int tickets) {
        Movie newMovie = new Movie();
        newMovie.year = year;
        newMovie.title = title;
        newMovie.tickets = tickets;
        return newMovie;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private int size() {
        return size;
    }

    public void insertAtTail(String title, int year, int tickets) {
        Movie newMovie = createMovie(title, year, tickets);
        if (last == null)
            first = newMovie;
        if (last != null) {
            newMovie.previous = last;
            last.next = newMovie;
        }
        last = newMovie;
        size++;
        isSorted = false;
    }

    public Movie removeFromStart() {
        Movie res = null;
        if (isEmpty())
            return res;
        res = first;
        if (size > 1) {
            first.next.previous = null;
            first = first.next;
        }
        if (size == 1) {
            last = null;
            first = null;
        }
        size--;
        return res;
    }

    public void insertSortedByTickets(String title, int year, int tickets) {
        if (isEmpty()) {
            return;
        }
        Movie newMovie = createMovie(title, year, tickets);
        if (!isSorted)
            sortByTickets();

        Movie currentMovie;
        for (currentMovie = first; currentMovie != null; currentMovie = currentMovie.next) {
            if (currentMovie.tickets > newMovie.tickets) {
                break;
            }
        }

        if (currentMovie != null) {
            Movie previousMovie = currentMovie.previous;
            currentMovie.previous = newMovie;
            newMovie.next = currentMovie;
            newMovie.previous = previousMovie;

            if (previousMovie == null) {
                // This is the first node
                first = newMovie;
            } else {
                newMovie.previous.next = newMovie;
            }
            size++;
        } else {
            // new year has the biggest number of tickets
            insertAtTail(title, year, tickets);
        }
    }

    public Movie findMovieWithName(String title) {
        if (isEmpty())
            return null;
        Movie current = last;
        while (current != null) {
            if (current.title == title)
                return current;
            current = current.previous;
        }
        return null;
    }

    public void reversePrint() {
        Movie current = last;
        while (current != null) {
            System.out.println(current);
            current = current.previous;
        }

    }

    public void swap(Movie current, Movie next) {

        if (current.previous == null)
            first = next;
        else
            current.previous.next = next;

        next.previous = current.previous;
        current.previous = next;
        current.next = next.next;
        next.next = current;
        if (current.next == null)
            last = current;
        else
            current.next.previous = current;
    }

    public void sortByTickets() {
        if (isEmpty() || size == 1)
            return;
        for (int i = 0; i < size - 1; i++) {
            Movie current = first;
            for (int j = 0; j < size - 1 - i; j++) {
                if (current.tickets > current.next.tickets) {
                    swap(current, current.next);
                } else
                    current = current.next;
            }
        }
        isSorted = true;
    }

    

    public void removeMoviesInYear(int year) {
        if (isEmpty())
            return;

        Movie currentMovie = first;

        while (currentMovie != null) {
            if (currentMovie.year == year) {
                Movie previousMovie = currentMovie.previous;
                Movie nextMovie = currentMovie.next;

                if (previousMovie != null) {
                    previousMovie.next = nextMovie;
                }
                if (nextMovie != null) {
                    nextMovie.previous = previousMovie;
                }

                if (currentMovie == first) {
                    first = nextMovie;
                }
                if (currentMovie == last) {
                    last = previousMovie;
                }
                size--;
            }
            currentMovie = currentMovie.next;
        }
    }

    @Override
    public Iterator<Movie> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Movie> {
        Movie current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Movie next() {
            Movie res = current;
            current = current.next;
            return res;
        }
    }

    public static void main(String[] args) {
        Pavlatos list = new Pavlatos();
        list.insertAtTail("starwars1", 2001, 1005);
        list.insertAtTail("starwars2", 2001, 1004);
        list.insertAtTail("starwars3", 2002, 1003);
        list.insertAtTail("starwars4", 2002, 1002);
        list.insertAtTail("starwars5", 2003, 1001);
        list.insertAtTail("starwars6", 2003, 1000);

        System.out.println("initial list:");
        for (Movie mv : list) {
            System.out.println(mv);
        }
        System.out.println("\nreverse print");
        list.reversePrint();

        list.removeFromStart();
        System.out.println("\nlist after removing first element: ");
        for (Movie mv : list) {
            System.out.println(mv);
        }
        System.out.println("\nreverse print");
        list.reversePrint();

        list.sortByTickets();
        System.out.println("\nsorted list by tickets");
        for (Movie mv : list) {
            System.out.println(mv);
        }

        System.out.println("\nreverse print");
        list.reversePrint();

        list.insertSortedByTickets("starwars7", 2004, 1500);
        System.out.println("\nList after adding movie with 1500 tickets");
        for (Movie mv : list) {
            System.out.println(mv);
        }
        System.out.println("\nreverse print");
        list.reversePrint();

        list.insertSortedByTickets("starwars8", 2005, 500);
        System.out.println("\nList after adding movie with 500 tickets");
        for (Movie mv : list) {
            System.out.println(mv);
        }
        System.out.println("\nreverse print");
        list.reversePrint();


        list.insertSortedByTickets("starwars9", 2005, 1000);
        System.out.println("\nList after adding movie with 1000 tickets");
        for (Movie mv : list) {
            System.out.println(mv);
        }
        System.out.println("\nreverse print");
        list.reversePrint();

        list.removeMoviesInYear(2003);
        System.out.println("\nList after removing movies from 2003");
        for (Movie mv : list) {
            System.out.println(mv);
        }
        System.out.println("\nreverse print");
        list.reversePrint();

        Movie result=list.findMovieWithName("starwars8");
        System.out.println("\nmovie with name: 'starwars8':  " +result );

    }
}