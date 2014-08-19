package vlashel.com.note.model;

import org.joda.time.DateTime;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 14.08.2014
 */
public class Note {
    private Integer id;
    private String title;
    private String description;
  //  private DateTime dateTime;

    public Note() {
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Note(Integer id, String title, String description ) {
        this.id = id;
        this.description = description;
        this.title = title;
    }

    public Note(String title, String description, DateTime dateTime) {
        this.title = title;
        this.description = description;
      //  this.dateTime = dateTime;
    }

    public Note(Integer id, String title, String description, DateTime dateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
      //  this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   /* public DateTime getDateTime() {
        return dateTime;
    }*/

    /*public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title;
    }
}
