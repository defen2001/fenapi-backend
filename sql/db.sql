use defengapi
-- 接口信息
create table if not exists defengapi.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '接口名称',
    `description` varchar(256) null comment '描述',
    `url` varchar(512) not null comment '接口地址',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `status` int default 0 not null comment '接口状态（0-关闭，1-开启）',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创建人',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `isDeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
    ) comment '接口信息';

insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('陆泽洋', '赵浩宇', 'www.beatrice-russel.info', '钱耀杰', '郑雨泽', 0, '阎旭尧', 38757800);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('范文轩', '任熠彤', 'www.jamel-howe.name', '冯思源', '郑鸿煊', 0, '唐子骞', 1450347226);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('王远航', '高子默', 'www.lenore-maggio.biz', '徐博涛', '金航', 0, '李思淼', 1);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('孟熠彤', '刘浩轩', 'www.jeremiah-kilback.net', '黄胤祥', '邵胤祥', 0, '邱凯瑞', 196718);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('何修杰', '龚弘文', 'www.livia-gleason.io', '任擎宇', '洪鑫鹏', 0, '卢文昊', 264462);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('杨峻熙', '蒋潇然', 'www.shawna-pouros.org', '吕凯瑞', '陆雪松', 0, '金浩宇', 399853);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('龚明哲', '韩瑞霖', 'www.jimmie-kub.info', '马锦程', '孔绍齐', 0, '赖智渊', 586253);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('贺烨霖', '汪思远', 'www.hugo-mcdermott.name', '叶炫明', '严思淼', 0, '钟峻熙', 31);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('任皓轩', '朱天宇', 'www.rhoda-cremin.co', '徐博超', '龚博文', 0, '周天宇', 85961);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('罗昊焱', '叶明', 'www.olivia-kunde.name', '顾荣轩', '杜子骞', 0, '孔哲瀚', 740);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('孔弘文', '魏瑞霖', 'www.serena-powlowski.name', '梁炎彬', '程嘉熙', 0, '谭煜祺', 662670203);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('姜潇然', '姚健雄', 'www.hung-okon.com', '莫晓啸', '韩驰', 0, '贾哲瀚', 6033062436);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('钱修杰', '江明哲', 'www.gaston-schuster.biz', '钟明哲', '方文博', 0, '卢熠彤', 307463);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('丁金鑫', '石明辉', 'www.teresia-bradtke.org', '宋钰轩', '阎潇然', 0, '邵志泽', 36722);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('周文博', '孟瑾瑜', 'www.narcisa-mosciski.name', '郭健柏', '范鹏', 0, '陈彬', 930987514);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('姜雪松', '钱思聪', 'www.susie-upton.org', '秦浩然', '戴泽洋', 0, '周文昊', 5);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('沈鑫磊', '刘明哲', 'www.devorah-aufderhar.info', '程天磊', '汪立辉', 0, '李立果', 6436826365);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('丁致远', '莫泽洋', 'www.garry-hodkiewicz.io', '姚嘉熙', '孟楷瑞', 0, '冯博涛', 6882520673);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('许泽洋', '石立果', 'www.karl-spinka.biz', '刘博超', '白昊然', 0, '韩文轩', 63);
insert into defengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('莫果', '郑烨霖', 'www.clarice-prosacco.info', '许志强', '于昊天', 0, '谢鹤轩', 333329);