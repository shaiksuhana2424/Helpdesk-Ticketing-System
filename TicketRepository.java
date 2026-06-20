package helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import helpdesk.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
