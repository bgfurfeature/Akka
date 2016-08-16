# Akka

* 使用最新发布的Akka, 必须用JDK1.8+

Actor trait 只定义了一个抽象方法，就是上面提到的 receive, 用来实现actor的行为。

如果当前 actor 的行为与收到的消息不匹配，则会调用 unhandled, 它的缺省实现是向actor系统的事件流中发布一条 akka.actor.UnhandledMessage(message, sender, recipient)。

另外，它还包括:

self 代表本actor的 ActorRef

sender 代表最近收到的消息的发送actor，通常用于下面将讲到的 回应消息中

supervisorStrategy 用户可重写它来定义对子actor的监管策略

context 暴露actor和当前消息的上下文信息，如：

用于创建子actor的工厂方法 (actorOf)

actor所属的系统

父监管者

所监管的子actor

生命周期监控

hotswap行为栈