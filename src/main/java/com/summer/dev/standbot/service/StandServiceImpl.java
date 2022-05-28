package com.summer.dev.standbot.service;

import com.summer.dev.standbot.constant.EquipmentStateEnum;
import com.summer.dev.standbot.constant.TelegramEmoji;
import com.summer.dev.standbot.entity.Stand;
import com.summer.dev.standbot.entity.EquipmentState;
import com.summer.dev.standbot.repository.StandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public void setStatus(Long id, EquipmentState status) {
        Stand stand = get(id);
        stand.setStandStatus(status);

        standRepository.save(stand);
    }

    @Override
    public List<Stand> getAll() {
        return standRepository.findAll();
    }

    @Override
    public String getStandsInfo() {
        List<Stand> stands = getAll();
        Function<Stand, String> funcToString = s ->
                String.format("Стенд: %3s, статус: %s", s.getName(), getEmojiFromStatus(s.getStandStatus()));

        return stands.stream()
                .map(funcToString)
                .collect(Collectors.joining("\n\n"));
    }

    @Override
    public String getStandInfo(String name) {
        Stand stand = getByName(name);

        StringBuilder sb = new StringBuilder();
        sb.append("*Стенд: ")
                .append(stand.getName())
                .append("*\n");
        sb.append("Статус стенда: ")
                .append(stand.getStandStatus().getName())
                .append("\n");
        sb.append("Статус метрик: ")
                .append(stand.getMetricsStatus().getName())
                .append("\n");
        sb.append("Статус НС: ")
                .append(stand.getIndependentSessionStatus().getName())
                .append("\n");
        sb.append("Статус ЗС: ")
                .append(stand.getDependentSessionStatus().getName())
                .append("\n");

        return sb.toString();
    }

    @Override
    public List<String> getStandsNames() {
        return standRepository.getStandsNames();
    }

    private String getEmojiFromStatus(EquipmentState status) {
        if (EquipmentStateEnum.EQUIPMENT_STATE_AVAILABLE.equalsStatus(status)) {
            return TelegramEmoji.OK_MARK.toUnicode();
        } else if (EquipmentStateEnum.EQUIPMENT_STATE_NOT_AVAILABLE.equalsStatus(status)) {
            return TelegramEmoji.ERROR_CHECK_MARK.toUnicode();
        } else if (EquipmentStateEnum.EQUIPMENT_STATE_UNSTABLE.equalsStatus(status)) {
            return TelegramEmoji.WARNING_CHECK_MARK.toUnicode();
        }

        throw new IllegalArgumentException("Unknown status, id: " + status.getId());
    }

    private Stand getByName(String name) {
        return standRepository.findByName(name)
                .orElseThrow(() -> new NullPointerException("Stand doesn't exist, name: " + name));
    }

}
