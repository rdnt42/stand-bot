package com.summer.dev.standbot.service.bot.command.encoder;

import com.summer.dev.standbot.constant.keyboard.CommandTags;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 09.06.2022
 * Time: 20:44
 */
@Service
public class CommandEncoderService {

    public String getConcatCommand(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
            sb.append(CommandTags.TAG_END.getTag());
        }

        return sb.toString();
    }
}
