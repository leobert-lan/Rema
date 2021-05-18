# Rema
Rema is short of Repository Manager, take first two letters of each word. Make it easier to host a repository of model, and content observer

Rema（雷玛）取自 **Re**pository **Ma**nager,致力于更简单的构建Model层仓库和内容变更订阅。

## 我为什么想到要创作Rema

这些年客户端的演变很大，分层架构涌现出MVC/MVP/MVVM/及变种,但是均没有深入Model层，即使优秀如Retrofit，亦不能算真正意义上的Model层封装框架，仅仅是基于OKhttp对Webservice进行定义、使用的网络层框架；

结合多年的开发经验，如果客户端开发工作中，真正将Model层的业务封装独立出来，业务的可测性和Model的复用性会大大提升，大多数的业务场景会因为“Repository”的出现而变得简单。

故而产生了这一想法，创作Rema用于构建、管理“Repository”，并有效的对Model层进行业务封装和维护

