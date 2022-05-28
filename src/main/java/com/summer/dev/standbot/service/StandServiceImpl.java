package com.summer.dev.standbot.service;

import com.summer.dev.standbot.constant.StandStatusEnum;
import com.summer.dev.standbot.constant.TelegramEmoji;
import com.summer.dev.standbot.entity.Stand;
import com.summer.dev.standbot.entity.StandStatus;
import com.summer.dev.standbot.repository.StandRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
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
    public void setStatus(Long id, StandStatus status) {
        Stand stand = get(id);
        stand.setStatus(status);

        standRepository.save(stand);
    }

    @Override
    public List<Stand> getAll() {
        return standRepository.findAll();
    }

    public String getStandsInfo() {
        List<Stand> stands = getAll();
        Function<Stand, String> funcToString = s ->
                String.format("Стенд: %3s , статус: %s", s.getName(), getEmojiFromStatus(s.getStatus()));

        return stands.stream()
                .map(funcToString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public List<String> getStandsNames() {
        return standRepository.getStandsNames();
    }

    private String getEmojiFromStatus(StandStatus status) {
        if (StandStatusEnum.STAND_STATUS_SUCCESS.equalsStatus(status)) {
            return TelegramEmoji.OK_MARK.toUnicode();
        } else if (StandStatusEnum.STAND_STATUS_WARNING.equalsStatus(status)) {
            return TelegramEmoji.WARNING_CHECK_MARK.toUnicode();
        } else if (StandStatusEnum.STAND_STATUS_ERROR.equalsStatus(status)) {
            return TelegramEmoji.ERROR_CHECK_MARK.toUnicode();
        }

        throw new IllegalArgumentException("Unknown status, id: " + status.getId());
    }

}
