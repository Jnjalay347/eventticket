package com.example.eventticket.service;

import com.example.eventticket.entity.Organizer;
import com.example.eventticket.repository.OrganizerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizerService {

    private final OrganizerRepository organizerRepository;

    public OrganizerService(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @Transactional
    public Organizer createOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }
}