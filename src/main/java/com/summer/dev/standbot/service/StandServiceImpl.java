package com.summer.dev.standbot.service;

import com.summer.dev.standbot.constant.EquipmentStatuses;
import com.summer.dev.standbot.constant.TelegramEmoji;
import com.summer.dev.standbot.constant.keyboard.EquipmentSelectCommands;
import com.summer.dev.standbot.constant.keyboard.StatusSelectCommands;
import com.summer.dev.standbot.entity.Stand;
import com.summer.dev.standbot.entity.Status;
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

        return "*Стенд:* " + stand.getName() + "\n" +
                "Статус стенда: " + stand.getStandStatus().getName() + "\n" +
                "Статус метрик: " + stand.getMetricsStatus().getName() + "\n" +
                "Статус НС: " + stand.getIndependentSessionStatus().getName() + "\n" +
                "Статус ЗС: " + stand.getDependentSessionStatus().getName() + "\n";
    }

    @Override
    public List<String> getStandsNames() {
        return standRepository.getStandsNames();
    }

    @Override
    public void changeStatus(String standName, String equipmentName, String newStatusName) {
        Stand stand = getByName(standName);
        EquipmentStatuses newStatus = getStatusFromStatusName(newStatusName);

        updateStatus(stand, equipmentName, newStatus);
        updateGeneralStatusIfNeed(stand, equipmentName, newStatus);

        standRepository.save(stand);
    }

    private EquipmentStatuses getStatusFromStatusName(String newStatusName) {
        StatusSelectCommands statusCommand = StatusSelectCommands.getByName(newStatusName);

        return getStatusFromStatusCommand(statusCommand);
    }

    private EquipmentStatuses getStatusFromStatusCommand(StatusSelectCommands statusCommand) {
        return switch (statusCommand) {
            case STATUS_TO_AVAILABLE -> EquipmentStatuses.EQUIPMENT_STATE_AVAILABLE;
            case STATUS_TO_UNAVAILABLE -> EquipmentStatuses.EQUIPMENT_STATE_UNAVAILABLE;
            case STATUS_TO_UNSTABLE -> EquipmentStatuses.EQUIPMENT_STATE_UNSTABLE;
        };
    }

    private void updateStatus(Stand stand, String equipmentName, EquipmentStatuses newStatus) {
        if (EquipmentSelectCommands.STAND.isCommand(equipmentName)) {
            stand.setStandStatusId(newStatus.getId());
        } else if (EquipmentSelectCommands.METRIC.isCommand(equipmentName)) {
            stand.setMetricsStatusId(newStatus.getId());
        } else if (EquipmentSelectCommands.DEPENDENT_SESSION.isCommand(equipmentName)) {
            stand.setDependentSessionStatusId(newStatus.getId());
        } else if (EquipmentSelectCommands.INDEPENDENT_SESSION.isCommand(equipmentName)) {
            stand.setIndependentSessionStatusId(newStatus.getId());
        }
    }

    private void updateGeneralStatusIfNeed(Stand stand, String equipmentName, EquipmentStatuses newStatus) {
        if (isStandUnstableDueTuEquipment(equipmentName, newStatus)) {
            stand.setStandStatusId(EquipmentStatuses.EQUIPMENT_STATE_UNSTABLE.getId());
        } else if (isStandStableDueTuEquipment(equipmentName, newStatus)) {
            updateAllToAvailable(stand);
        } else if (isStandUnavailable(equipmentName, newStatus)) {
            updateAllToUnavailable(stand);
        }
    }

    private void updateAllToAvailable(Stand stand) {
        int availableId = EquipmentStatuses.EQUIPMENT_STATE_AVAILABLE.getId();
        stand.setMetricsStatusId(availableId);
        stand.setIndependentSessionStatusId(availableId);
        stand.setDependentSessionStatusId(availableId);
    }

    private void updateAllToUnavailable(Stand stand) {
        int unavailableId = EquipmentStatuses.EQUIPMENT_STATE_UNAVAILABLE.getId();
        stand.setMetricsStatusId(unavailableId);
        stand.setIndependentSessionStatusId(unavailableId);
        stand.setDependentSessionStatusId(unavailableId);
    }

    private boolean isStandUnstableDueTuEquipment(String equipmentName, EquipmentStatuses newStatus) {
        return !EquipmentSelectCommands.STAND.isCommand(equipmentName) &&
                EquipmentStatuses.EQUIPMENT_STATE_AVAILABLE != newStatus;
    }

    private boolean isStandStableDueTuEquipment(String equipmentName, EquipmentStatuses newStatus) {
        return EquipmentSelectCommands.STAND.isCommand(equipmentName) &&
                EquipmentStatuses.EQUIPMENT_STATE_AVAILABLE == newStatus;
    }

    private boolean isStandUnavailable(String equipmentName, EquipmentStatuses newStatus) {
        return EquipmentSelectCommands.STAND.isCommand(equipmentName) &&
                EquipmentStatuses.EQUIPMENT_STATE_UNAVAILABLE == newStatus;
    }

    private String getEmojiFromStatus(Status status) {
        if (EquipmentStatuses.EQUIPMENT_STATE_AVAILABLE.equalsStatus(status)) {
            return TelegramEmoji.OK_MARK.toUnicode();
        } else if (EquipmentStatuses.EQUIPMENT_STATE_UNAVAILABLE.equalsStatus(status)) {
            return TelegramEmoji.ERROR_CHECK_MARK.toUnicode();
        } else if (EquipmentStatuses.EQUIPMENT_STATE_UNSTABLE.equalsStatus(status)) {
            return TelegramEmoji.WARNING_CHECK_MARK.toUnicode();
        }

        throw new IllegalArgumentException("Unknown status, id: " + status.getId());
    }

    private Stand getByName(String name) {
        return standRepository.findByName(name)
                .orElseThrow(() -> new NullPointerException("Stand doesn't exist, name: " + name));
    }

}
