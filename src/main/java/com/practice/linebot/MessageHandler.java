package com.practice.linebot;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.Separator;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.component.Text.TextWeight;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.quickreply.QuickReply;
import com.linecorp.bot.model.message.quickreply.QuickReplyItem;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.RequiredArgsConstructor;

@LineMessageHandler
@RequiredArgsConstructor
public class MessageHandler {
    private final LineMessagingClient client;
    private static final String DOMAIN = "e86413d8";

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        return TextMessage.builder().text(event.getMessage().getText()).build();
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }

    @EventMapping
    public void handleFollowEvent(FollowEvent event) throws ExecutionException, InterruptedException {
        final String token = event.getReplyToken();
        follow(token);
    }

    @EventMapping
    public void handlePostBackEvent(PostbackEvent event) throws ExecutionException, InterruptedException {
        final String token = event.getReplyToken();
        final String data = event.getPostbackContent().getData();
        List<Message> messages = null;
        if (data.startsWith("yes") || data.startsWith("no")) {
            final String[] datas = data.split("_");
            switch (datas[0]) {
                case "yes":
                    messages = Collections.singletonList(TextMessage.builder().text(datas[1].equals("men") ?
                                                                                    "ではあなたを「男性」と認めます。" :
                                                                                    "ではあなたを「女性」と認めます。")
                                                                    .build());
                    break;
                case "no":
                    messages = Collections.singletonList(TextMessage.builder().text(datas[1].equals("men") ?
                                                                                    "やはりあなたは「男性」でしたか。" :
                                                                                    "やはりあなたは「女性」でしたか。")
                                                                    .build());
                    break;
            }
        } else {
            switch (data) {
                case "men":
                    messages = Arrays.asList(
                            TextMessage.builder().text("回答ありがとうございます。").build(),
//                            FlexMessage.builder().altText("男性").contents(
//                                    Bubble.builder()
//                                          .body(Box.builder().layout(FlexLayout.VERTICAL)
//                                                   .contents(Arrays.asList(
//                                                           Text.builder().text("あなたは「男性」ですね？")
//                                                               .size(FlexFontSize.LG)
//                                                               .build(),
//                                                           Separator.builder().build())
//                                                   ).build())
//                                          .footer(Box.builder().layout(FlexLayout.HORIZONTAL).contents(
//                                                  Arrays.asList(
//                                                          Button.builder()
//                                                                .action(new PostbackAction("はい", "yes_men"))
//                                                                .build(),
//                                                          Button.builder()
//                                                                .action(new PostbackAction("いいえ", "no_women"))
//                                                                .build()
//                                                  )
//                                          ).build())
//                                          .build()
//                            ).build()
                            TextMessage.builder().text("あなたは「男性」ですね？").quickReply(QuickReply.builder().items(
                                    Arrays.asList(
                                            QuickReplyItem.builder().action(new PostbackAction("はい", "yes_men"))
                                                          .build(),
                                            QuickReplyItem.builder()
                                                          .action(new PostbackAction("いいえ", "no_women"))
                                                          .build()
                                    )
                            ).build()).build()
                    );
                    break;
                case "women":
                    messages = Arrays.asList(
                            TextMessage.builder().text("回答ありがとうございます。").build(),
//                            FlexMessage.builder().altText("女性").contents(
//                                    Bubble.builder()
//                                          .body(Box.builder().layout(FlexLayout.VERTICAL)
//                                                   .contents(Arrays.asList(
//                                                           Text.builder().text("あなたは「女性」ですね？")
//                                                               .size(FlexFontSize.LG)
//                                                               .build(),
//                                                           Separator.builder().build())
//                                                   ).build())
//                                          .footer(Box.builder().layout(FlexLayout.HORIZONTAL).contents(
//                                                  Arrays.asList(
//                                                          Button.builder()
//                                                                .action(new PostbackAction("はい", "yes_women"))
//                                                                .build(),
//                                                          Button.builder()
//                                                                .action(new PostbackAction("いいえ", "no_men"))
//                                                                .build()
//                                                  )
//                                          ).build())
//                                          .build()
//                            ).build()
                            TextMessage.builder().text("あなたは「女性」ですね？").quickReply(QuickReply.builder().items(
                                    Arrays.asList(
                                            QuickReplyItem.builder().action(
                                                    new PostbackAction("はい", "yes_women"))
                                                          .build(),
                                            QuickReplyItem.builder().action(new PostbackAction("いいえ", "no_men"))
                                                          .build()
                                    )
                            ).build()).build()
                    );
                    break;
            }
        }
        client.replyMessage(new ReplyMessage(token, messages));
    }

    public void follow(String token) throws ExecutionException, InterruptedException {
        client.replyMessage(new ReplyMessage(token, createFollowMessage())).get();
    }

//    private List<Message> createFollowMessage() {
//        return Arrays.asList(
//                new TextMessage("友達登録ありがとうございます！"),
//                new TextMessage("ところで、あなたの性別は？"),
//                FlexMessage.builder().altText("性別").contents(
//                        Carousel.builder().contents(Arrays.asList(
//                                Bubble.builder().body(
//                                        Box.builder().layout(FlexLayout.HORIZONTAL).contents(Collections.singletonList(Button.builder().action(new PostbackAction("男性", "men")).build()))
//                                           .build())
//                                      .build(),
//                                Bubble.builder().body(
//                                        Box.builder().layout(FlexLayout.HORIZONTAL).contents(Collections.singletonList(Button.builder().action(new PostbackAction("女性", "women")).build()))
//                                           .build())
//                                      .build()
//                        )).build()
//                ).build()
//        );
//    }

    private List<Message> createFollowMessage() {
        final Box text = Box.builder().layout(FlexLayout.VERTICAL).contents(
                Arrays.asList(
                        Text.builder().text("ところで、あなたの性別は？").weight(TextWeight.BOLD).size(FlexFontSize.Md)
                            .build(),
                        Separator.builder().build()
                )
        ).build();
        final Box answer = Box.builder().layout(FlexLayout.HORIZONTAL).contents(
                Arrays.asList(
                        Button.builder().action(new PostbackAction("男性", "men")).build(),
                        Button.builder().action(new PostbackAction("女性", "women")).build()
                )
        ).build();
        return Arrays.asList(
                new TextMessage("友達登録ありがとうございます！"),
                FlexMessage.builder().altText("性別").contents(
                        Bubble.builder()
                              .body(text)
                              .footer(answer)
                              .build()
                ).build()
        );
    }
}
