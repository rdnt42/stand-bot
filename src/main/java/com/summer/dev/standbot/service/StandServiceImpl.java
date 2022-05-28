package com.summer.dev.standbot.service;

import com.summer.dev.standbot.entity.Stand;
import com.summer.dev.standbot.entity.StandStatus;
import com.summer.dev.standbot.repository.StandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.05.2022
 * Time: 15:44
 */
@AllArgsConstructor
@Service
public class StandServiceImpl implements StandService {
    private final StandRepository standRepository;

    @Override
    public Stand get(Long id) {
        return standRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Stand doesn't exist, id: " + id));
    }

    @Override
    public void setStatus(Long id, StandStatus status) {
        Stand stand = get(id);
        stand.setStatus(status);

        standRepository.save(stand);
    }

    @Override
    public List<Stand> getAll() {
        return standRepository.findAll();
    }
}
