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
    venue_id INT REFERENCES events(venue_id) ON DELETE CASCADE ,
    attendee_id INT REFERENCES attendees(attendee_id) ON DELETE CASCADE
);