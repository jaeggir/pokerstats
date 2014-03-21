create table T_SEASON (
      _UUID varchar(255) not null,
      _CURRENT boolean,
      _NAME varchar(255) not null,
      primary key (_UUID)
);

create table T_EVENT (
    _UUID varchar(255) not null,
    _DATE date,
    _NAME varchar(255),
    _HOST varchar(255),
    _SEASON bigint,
    _UPDATED timestamp,
    primary key (_UUID)
);

create table T_PLAYER (
    _UUID varchar(255) not null,
    _BIRTHDAY date,
    _GUEST boolean,
    _JOINED date,
    _NICKNAME varchar(255),
    _UPDATED timestamp,
    primary key (_UUID)
);

create table T_TOURNAMENT (
    _UUID varchar(255) not null,
    _EVENT varchar(255),
    _RESULT_SUBMITTED_BY varchar(255),
    _ROUND integer,
    _STATUS integer,
    _UPDATED timestamp,
    primary key (_UUID)
);

alter table T_SEASON add constraint UK_qwxec7uhaopnq19bk28a7r658  unique (_NAME);
alter table T_EVENT add constraint FK_44sa6b9uevtllgyf6988mkxjh foreign key (_SEASON) references T_SEASON;
alter table T_EVENT add constraint FK_28n9jo2bdkkymb1r0erpqbviq foreign key (_HOST) references T_PLAYER;
alter table T_TOURNAMENT add constraint FK_cxfn1ad9687ab6frvhn35w61i foreign key (_EVENT) references T_EVENT;
alter table T_TOURNAMENT add constraint FK_tn04myhwaxkg17ifnk9q40pis foreign key (_RESULT_SUBMITTED_BY) references T_PLAYER;