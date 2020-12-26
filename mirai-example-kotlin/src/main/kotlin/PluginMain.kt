package org.example.mirai.plugin

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.NewFriendRequestEvent
import net.mamoe.mirai.utils.info
import net.mamoe.mirai.event.subscribeAlways
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.PlainText

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

        subscribeAlways<GroupMessageEvent> {
            //Ⱥ��Ϣ
            if(message.contentToString().startsWith("����")){
                //reply Ҳ���Ի��� group.sendMessage()��reply���Զ��˳����û�Ǵ�sendmessage����
                reply(message.contentToString().replace("����", ""))
            }
            if(message.contentToString() == "hi"){
                reply("hi")
            }
            message.forEach {
                //ѭ��ÿ��Ԫ������Ϣ��
                if(it is Image){
                    //�����Ϣ��һ������ͼƬ
                }
                if(it is PlainText){
                    //�����Ϣ��һ�����Ǵ��ı�
                }
            }
        }
        subscribeAlways<FriendMessageEvent>{
            //������Ϣ
        }
        subscribeAlways<NewFriendRequestEvent> {
            //�Զ�ͬ���������
            accept()
        }
        subscribeAlways<BotInvitedJoinGroupRequestEvent> {
            //�Զ�ͬ���Ⱥ����
            accept()
        }
    }
}
