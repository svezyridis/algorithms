/**
 * Movie
 */
public class Movie {

    int year;
    String title;
    int tickets;
    Movie next;
    Movie previous;

    @Override
    public String toString() {
        // TODO Auto-generated method stub

        return "Title: " + title + " Year: " + year + " Tickets: " + tickets;
    }

    public static void main(String[] args) {
        Pavlatos list=new Pavlatos();

        list.insertAtTail("savvas", 1994, 1994);
        System.out.println(list.findMovieWithName("savvas"));
    }

}