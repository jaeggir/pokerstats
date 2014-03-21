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
    _SEASON varchar(255),
    _UPDATED timestamp,
    _VENUE varchar(255),
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

create table T_TOURNAMENT_RESULT (
    _UUID varchar(255) not null,
    _ELIMINATED_BY varchar(255),
    _PLAYER varchar(255),
    _POINTS integer,
    _RANK integer,
    _ANTE integer,
    _WIN integer,
    _TOURNAMENT varchar(255),
    _UPDATED timestamp,
    primary key (_UUID)
);

create table T_VENUE (
    _UUID varchar(255) not null,
    _CITY varchar(255),
    _COUNTRY varchar(255),
    _CURRENT boolean,
    _LATITUDE numeric,
    _LONGITUDE numeric,
    _NAME varchar(255),
    _STREET varchar(255),
    _UPDATED timestamp,
    _ZIP integer,
    primary key (_UUID)
);

alter table T_SEASON add constraint UK_qwxec7uhaopnq19bk28a7r658  unique (_NAME);
alter table T_EVENT add constraint FK_28n9jo2bdkkymb1r0erpqbviq foreign key (_HOST) references T_PLAYER;
alter table T_EVENT add constraint FK_44sa6b9uevtllgyf6988mkxjh foreign key (_SEASON) references T_SEASON;
alter table T_EVENT add constraint FK_4140ljluvj0x0j2r9fhsirxwn foreign key (_VENUE) references T_VENUE;
alter table T_TOURNAMENT add constraint FK_cxfn1ad9687ab6frvhn35w61i foreign key (_EVENT) references T_EVENT;
alter table T_TOURNAMENT add constraint FK_tn04myhwaxkg17ifnk9q40pis foreign key (_RESULT_SUBMITTED_BY) references T_PLAYER;
alter table T_TOURNAMENT_RESULT add constraint FK_hql7dmrbxb0fee1l7s8mayfqc foreign key (_TOURNAMENT) references T_TOURNAMENT;