package com.investment.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket_quote")
@Getter
@Setter
public class TicketQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "quote_sequence_generator")
    @SequenceGenerator(name="ticket_quote_sequence_generator", sequenceName = "quote_sequence_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private LocalDateTime date;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal close;

    private Long volume;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

}
