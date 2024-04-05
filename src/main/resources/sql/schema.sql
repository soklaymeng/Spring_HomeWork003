DROP TABLE venues;

CREATE TABLE venues(
    venue_id SERIAL PRIMARY KEY ,
    venue_name varchar(150) NOT NULL ,
    location varchar(300) NOT NULL
);
CREATE TABLE attendees(
    attendee_id SERIAL PRIMARY KEY ,
    attendee_name varchar(150) NOT NULL ,
    email varchar(300) NOT NULL
);
CREATE TABLE events(
    event_id SERIAL PRIMARY KEY ,
    event_name varchar(150) NOT NULL ,
    event_date date,
    venue_id INTEGER REFERENCES venues(venue_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE event_attendee(
    id SERIAL PRIMARY KEY ,
    event_id INT REFERENCES events(event_id) ON DELETE CASCADE ,
    attendee_id INT REFERENCES attendees(attendee_id) ON DELETE CASCADE
);

SELECT e.venue_id,e.event_name,e.event_date,e.venue_id
FROM event_attendee a JOIN events e ON a.event_id= e.event_id
WHERE attendee_id=1;

SELECT * FROM attendees
JOIN event_attendee ea on attendees.attendee_id = ea.attendee_id
WHERE event_id= 1



