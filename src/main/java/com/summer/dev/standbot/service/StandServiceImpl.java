package com.summer.dev.standbot.service;

import com.summer.dev.standbot.constant.EquipmentStatuses;
import com.summer.dev.standbot.constant.TelegramEmoji;
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

    @Override
    public void changeStatus(String standName, String equipmentName, String newStatusName) {
        Stand stand = getByName(standName);
        EquipmentStatusEnum newStatus = getStatusFromAction(action);

        updateStatusFromCommand(stand, equipmentName, newStatus);
        updateGeneralStatusIfNeed(stand, equipmentName, newStatus);

        standRepository.save(stand);
    }

    private EquipmentStatusEnum getStatusFromAction(String action) {
        return null;
//        return switch (action) {
//            case ChangeStatusCommand.TO_AVAILABLE -> EquipmentStatusEnum.EQUIPMENT_STATE_AVAILABLE;
//            case ChangeStatusCommand.TO_UNAVAILABLE -> EquipmentStatusEnum.EQUIPMENT_STATE_UNAVAILABLE;
//            case ChangeStatusCommand.TO_UNSTABLE -> EquipmentStatusEnum.EQUIPMENT_STATE_UNSTABLE;
//            default -> throw new IllegalArgumentException("Unknown action: " + action);
//        };
    }

    private void updateStatusFromCommand(Stand stand, String equipmentName, EquipmentStatusEnum newStatus) {
//        if (EquipmentSelectCommand.STAND.equals(equipmentName)) {
//            stand.setStandStatusId(newStatus.getId());
//        } else if (EquipmentSelectCommand.METRIC.equals(equipmentName)) {
//            stand.setMetricsStatusId(newStatus.getId());
//        } else if (EquipmentSelectCommand.DEPENDENT_SESSION.equals(equipmentName)) {
//            stand.setDependentSessionStatusId(newStatus.getId());
//        } else if (EquipmentSelectCommand.INDEPENDENT_SESSION.equals(equipmentName)) {
//            stand.setIndependentSessionStatusId(newStatus.getId());
//        } else {
//            throw new IllegalArgumentException("Unknown equipment name: " + equipmentName);
//        }
    }

    private void updateGeneralStatusIfNeed(Stand stand, String equipmentName, EquipmentStatusEnum newStatus) {
        if (isStandUnstableDueTuEquipment(equipmentName, newStatus)) {
            stand.setStandStatusId(EquipmentStatusEnum.EQUIPMENT_STATE_UNSTABLE.getId());
        } else if (isStandStableDueTuEquipment(equipmentName, newStatus)) {
            stand.setMetricsStatusId(EquipmentStatusEnum.EQUIPMENT_STATE_AVAILABLE.getId());
            stand.setIndependentSessionStatusId(EquipmentStatusEnum.EQUIPMENT_STATE_AVAILABLE.getId());
            stand.setDependentSessionStatusId(EquipmentStatusEnum.EQUIPMENT_STATE_AVAILABLE.getId());
        } else if (isStandUnavailable(equipmentName, newStatus)) {
            stand.setMetricsStatusId(EquipmentStatusEnum.EQUIPMENT_STATE_UNAVAILABLE.getId());
            stand.setIndependentSessionStatusId(EquipmentStatusEnum.EQUIPMENT_STATE_UNAVAILABLE.getId());
            stand.setDependentSessionStatusId(EquipmentStatusEnum.EQUIPMENT_STATE_UNAVAILABLE.getId());
        }
    }

    private boolean isStandUnstableDueTuEquipment(String equipmentName, EquipmentStatusEnum newStatus) {
//        return !EquipmentSelectCommand.STAND.equals(equipmentName) &&
//                EquipmentStatusEnum.EQUIPMENT_STATE_AVAILABLE != newStatus;
        return false;
    }

    private boolean isStandStableDueTuEquipment(String equipmentName, EquipmentStatusEnum newStatus) {
//        return EquipmentSelectCommand.STAND.equals(equipmentName) &&
//                EquipmentStatusEnum.EQUIPMENT_STATE_AVAILABLE == newStatus;
        return false;
    }

    private boolean isStandUnavailable(String equipmentName, EquipmentStatusEnum newStatus) {
        return false;
//        return EquipmentSelectCommand.STAND.equals(equipmentName) &&
//                EquipmentStatusEnum.EQUIPMENT_STATE_UNAVAILABLE == newStatus;
    }

    private String getEmojiFromStatus(Status status) {
        if (EquipmentStatusEnum.EQUIPMENT_STATE_AVAILABLE.equalsStatus(status)) {
            return TelegramEmoji.OK_MARK.toUnicode();
        } else if (EquipmentStatusEnum.EQUIPMENT_STATE_UNAVAILABLE.equalsStatus(status)) {
            return TelegramEmoji.ERROR_CHECK_MARK.toUnicode();
        } else if (EquipmentStatusEnum.EQUIPMENT_STATE_UNSTABLE.equalsStatus(status)) {
            return TelegramEmoji.WARNING_CHECK_MARK.toUnicode();
        }

        throw new IllegalArgumentException("Unknown status, id: " + status.getId());
    }

    private Stand getByName(String name) {
        return standRepository.findByName(name)
                .orElseThrow(() -> new NullPointerException("Stand doesn't exist, name: " + name));
    }

}
