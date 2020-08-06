/*
* Name:sample example of kotlin mirai
* author:Eritque arcus
* blog:https://blog.csdn.net/qq_40832960
 */
package com.example.plugin

import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.plugins.PluginBase
import net.mamoe.mirai.event.*
import net.mamoe.mirai.event.events.NewFriendRequestEvent
import net.mamoe.mirai.event.MessageSubscribersBuilder
import net.mamoe.mirai.message.MessageEvent

object ExamplePluginMain : PluginBase() {

    override fun onEnable() {
        super.onEnable()
        //����Ѽ���
        logger.info("Plugin loaded!")

        //���⴦����Ϣ
        subscribeMessages {
            //����ȫ������Ϣ
            "������յ���Ϣ�����" reply{"�������"}
            "ǩ��" reply {"Hi ${sender.nick}\n ���ǻ���ά����"}
            "����" reply {"$message"}
        }
        subscribeGroupMessages {
            //����Ⱥ��Ϣ
            "Hi" reply {"hi ${sender.nick}"}
        }
        subscribeFriendMessages {
            //���������Ϣ
            "���" reply { "hi" }
            "������" reply{ "h" }
        }
        subscribeTempMessages {
            //������ʱ��Ϣ
        }
        subscribeAlways<NewFriendRequestEvent> {
            //�Զ�ͬ��Ӻ���
            this.accept()
        }

    }
}