package ch.makery.eventschool.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EventSchool {
	
	 private final StringProperty title;
	 private final StringProperty typeEvent;
	 private final StringProperty schoolSubject;
	 private final StringProperty description;
	 private final ObjectProperty<LocalDate> eventDate;
	 
	 public EventSchool() {
	        this(null, null);
	    }


	 public EventSchool(String title, String typeEvent) {
	        this.title = new SimpleStringProperty(title);
	        this.typeEvent = new SimpleStringProperty(typeEvent);
	        this.schoolSubject = new SimpleStringProperty(" ");
	        this.description = new SimpleStringProperty(" ");
	        this.eventDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	 }
	 
	 public String getTitle() {
		return title.get();
	 }
	 
	 public String getTypeEvent() {
		return typeEvent.get();
	 }
	 
	 public String getSchoolSubject() {
		return schoolSubject.get();
	 }
	 
	 public String getDescription() {
		return description.get();
	 }
	
	 public LocalDate getEventDate() {
		return eventDate.get();
	 }
	 
	 public void setTitle(String title) {
	        this.title.set(title);
	    }
	
	 public void setTypeEvent(String typeEvent) {
	        this.typeEvent.set(typeEvent);
	    }
	 
	 public void setSchoolSubject(String schoolSubject) {
	        this.schoolSubject.set(schoolSubject);
	    }
	 
	 public void setDescription(String description) {
	        this.description.set(description);
	    }
	 
	 public void setEventDate(LocalDate eventDate) {
	        this.eventDate.set(eventDate);
	    }
	 
	 public StringProperty titleProperty() {
	        return title;
	    }
	 
	 public StringProperty typeEventProperty() {
	        return typeEvent;
	    }
	 
	 public StringProperty schoolSubjectProperty() {
	        return schoolSubject;
	    }
	 
	 public StringProperty descriptionProperty() {
	        return description;
	    }
	 
	 public ObjectProperty<LocalDate> eventDateProperty() {
	        return eventDate;
	    }
	 
}