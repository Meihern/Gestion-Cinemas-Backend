package com.cinema.backend.entities.projections;

import com.cinema.backend.entities.Client;
import com.cinema.backend.entities.Place;
import com.cinema.backend.entities.Ticket;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="ticketProj", types={Ticket.class})
public interface TicketProject {
    Long getId();
    Client getClient();
    double getPrix();
    Integer getCodePayement();
    boolean getReservee();
    Place getPlace();
}
