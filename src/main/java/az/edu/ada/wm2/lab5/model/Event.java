package az.edu.ada.wm2.lab5.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Event {

    private UUID id;
    private String eventName;
    private List<String> tags;
    private BigDecimal ticketPrice;
    private LocalDateTime eventDateTime;
    private int durationMinutes;

    // No-args constructor
    public Event() {
        this.tags = new ArrayList<>();
    }

    // Constructor without id (for create operations)
    public Event(String eventName, List<String> tags, BigDecimal ticketPrice,
                 LocalDateTime eventDateTime, int durationMinutes) {
        this.eventName = eventName;
        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
        this.ticketPrice = ticketPrice;
        this.eventDateTime = eventDateTime;
        this.durationMinutes = durationMinutes;
    }

    // Constructor with all fields (for update operations)
    public Event(UUID id, String eventName, List<String> tags, BigDecimal ticketPrice,
                 LocalDateTime eventDateTime, int durationMinutes) {
        this.id = id;
        this.eventName = eventName;
        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
        this.ticketPrice = ticketPrice;
        this.eventDateTime = eventDateTime;
        this.durationMinutes = durationMinutes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
    }

    public void addTag(String tag) {
        if (tag == null || tag.isBlank()) return;

        if (tags == null) {
            tags = new ArrayList<>(List.of(tag));
        }

        if (tags.contains(tag)) return;

        this.tags.add(tag);
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", tags=" + tags +
                ", ticketPrice=" + ticketPrice +
                ", eventDateTime=" + eventDateTime +
                ", durationMinutes=" + durationMinutes +
                '}';
    }

    public LocalDateTime getDate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDate'");
    }

    public BigDecimal getPrice() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrice'");
    }

    public Object getTag() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTag'");
    }

    public void setPrice(BigDecimal newPrice) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPrice'");
    }
}