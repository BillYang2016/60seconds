package com.billyang.news;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.message.data.QuoteReply;

import java.io.File;
import java.util.Calendar;
import java.util.Timer;

public final class JavaPluginMain extends JavaPlugin {
    public static final JavaPluginMain INSTANCE = new JavaPluginMain();
    private JavaPluginMain() {
        super(new JvmPluginDescriptionBuilder("60seconds", "0.1.0")
                .id("com.billyang.news")
                .info("60s of the world")
                .author("Bill Yang")
                .build());
    }

    @Override
    public void onEnable() {

        String path = getDataFolder().getAbsolutePath();

        Timer timer = new Timer();
        timer.schedule(new Spider(path), 5000, 6 * 60 * 60 * 1000); //6小时更新一次

        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, g -> {
            //监听群消息
            String msg = g.getMessage().contentToString();
            if(msg.equals("今日新闻") || msg.equals("60s")) {
                getLogger().info("已收到命令请求！");
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR), month = c.get(Calendar.MONTH) + 1, day = c.get(Calendar.DAY_OF_MONTH);
                File file = new File(path, year + "-" + month + "-" + day + ".png");
                if(!file.exists()) new Spider(path).run();
                if(!file.exists()) g.getGroup().sendMessage(new QuoteReply(g.getSource()).plus("出错啦！"));
                else {
                    MessageChainBuilder builder = new MessageChainBuilder();
                    try {
                        Image img = Contact.uploadImage(g.getGroup(), file);
                        builder.append(img);
                    } catch (Exception e) {
                        builder.append(new PlainText("[图片尺寸过大]"));
                    }
                    g.getGroup().sendMessage(new QuoteReply(g.getSource()).plus(builder.build()));
                }
            }
        });
        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class, f -> {
            //监听好友消息
            String msg = f.getMessage().contentToString();
            if(msg.equals("今日新闻") || msg.equals("60s")) {
                getLogger().info("已收到命令请求！");
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR), month = c.get(Calendar.MONTH) + 1, day = c.get(Calendar.DAY_OF_MONTH);
                File file = new File(path, year + "-" + month + "-" + day + ".png");
                if(!file.exists()) new Spider(path).run();
                if(!file.exists()) f.getFriend().sendMessage("出错啦！");
                else {
                    MessageChainBuilder builder = new MessageChainBuilder();
                    try {
                        Image img = Contact.uploadImage(f.getFriend(), file);
                        builder.append(img);
                    } catch (Exception e) {
                        builder.append(new PlainText("[图片尺寸过大]"));
                    }
                    f.getFriend().sendMessage(builder.build());
                }
            }
        });
    }
}