package az.edu.ada.wm2.lab5.controller; 

import az.edu.ada.wm2.lab5.model.Event;
import az.edu.ada.wm2.lab5.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // 1. CREATE - POST /api/events
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        try {
            Event createdEvent = eventService.createEvent(event);
            return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 2. LIST ALL - GET /api/events
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        try {
            List<Event> events = eventService.getAllEvents();
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 3. GET ONE BY ID - GET /api/events/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable UUID id) {
        try {
            Event event = eventService.getEventById(id);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 4. REMOVE BY ID - DELETE /api/events/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        try {
            eventService.deleteEvent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 5. FULL UPDATE (PUT) - PUT /api/events/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable UUID id, @RequestBody Event event) {
        try {
            Event updatedEvent = eventService.updateEvent(id, event);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 6. PARTIAL UPDATE (PATCH) - PATCH /api/events/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<Event> partialUpdateEvent(@PathVariable UUID id, @RequestBody Event partialEvent) {
        try {
            Event updatedEvent = eventService.partialUpdateEvent(id, partialEvent);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

@GetMapping("/filter/tag")
public ResponseEntity<List<Event>> getEventsByTag(@RequestParam String tag) {
    List<Event> events = eventService.getEventsByTag(tag);
    return ResponseEntity.ok(events);
}

@GetMapping("/filter/price")
public ResponseEntity<List<Event>> getEventsByPriceRange(
        @RequestParam BigDecimal min,
        @RequestParam BigDecimal max) {

    List<Event> events = eventService.getEventsByPriceRange(min, max);
    return ResponseEntity.ok(events);
}

@GetMapping("/upcoming")
public ResponseEntity<List<Event>> getUpcomingEvents() {
    List<Event> events = eventService.getUpcomingEvents();
    return ResponseEntity.ok(events);
}

@PatchMapping("/{id}/price")
public ResponseEntity<Event> updateEventPrice(
        @PathVariable UUID id,
        @RequestParam(required = false) BigDecimal price) {

    if (price == null) {
        return ResponseEntity.badRequest().build();
    }

    if (price.compareTo(BigDecimal.ZERO) < 0) {
        return ResponseEntity.badRequest().build();
    }

    Event updated = eventService.updateEventPrice(id, price);

    if (updated == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(updated);
}

@GetMapping("/filter/date")
public ResponseEntity<List<Event>> getEventsByDateRange(
        @RequestParam LocalDateTime start,
        @RequestParam LocalDateTime end) {

    if (start == null || end == null || start.isAfter(end)) {
        return ResponseEntity.badRequest().build();
    }

    List<Event> events = eventService.getEventsByDateRange(start, end);
    return ResponseEntity.ok(events);
}