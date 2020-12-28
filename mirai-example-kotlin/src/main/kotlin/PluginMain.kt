package org.example.mirai.plugin

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.Listener
import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.NewFriendRequestEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.utils.info
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.PlainText
import kotlin.coroutines.EmptyCoroutineContext

/*
��src/main/resources/plugin.yml��Ĳ����Ϣ����ڵ�
��settings.gradle.kts������ɵĲ��.jar����
��runmiraikt������ÿ�����ide�����У����ø��Ƶ�mcl������������
 */

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "org.example.mirai-example",
        version = "0.1.0"
    )
) {
    override fun onEnable() {
        logger.info { "Plugin loaded" }
        //�����ļ�Ŀ¼ "${dataFolder.absolutePath}/"

        globalEventChannel().subscribeAlways(
            GroupMessageEvent::class,
            EmptyCoroutineContext,
            Listener.ConcurrencyKind.CONCURRENT
        ) {
            //Ⱥ��Ϣ
            if (message.contentToString().startsWith("����")) {
                group.sendMessage(message.contentToString().replace("����", ""))
            }
            if (message.contentToString() == "hi") {
                group.sendMessage("hi")
            }
            message.forEach {
                //ѭ��ÿ��Ԫ������Ϣ��
                if (it is Image) {
                    //�����Ϣ��һ������ͼƬ
                }
                if (it is PlainText) {
                    //�����Ϣ��һ�����Ǵ��ı�
                }
            }
        }
        globalEventChannel().subscribeAlways(
            FriendMessageEvent::class,
            EmptyCoroutineContext,
            Listener.ConcurrencyKind.CONCURRENT
        ) {
            //������Ϣ
        }
        globalEventChannel().subscribeAlways(
            NewFriendRequestEvent::class,
            EmptyCoroutineContext,
            Listener.ConcurrencyKind.CONCURRENT
        ) {
            //�Զ�ͬ���������
            accept()
        }
        globalEventChannel().subscribeAlways(
            BotInvitedJoinGroupRequestEvent::class,
            EmptyCoroutineContext,
            Listener.ConcurrencyKind.CONCURRENT
        ) {
            //�Զ�ͬ���Ⱥ����
            accept()
        }
    }
}
