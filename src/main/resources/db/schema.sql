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
    _SEASON bigint,
    _UPDATED timestamp,
    primary key (_UUID)
);

alter table T_SEASON add constraint UK_qwxec7uhaopnq19bk28a7r658  unique (_NAME);
alter table T_EVENT add constraint FK_44sa6b9uevtllgyf6988mkxjh foreign key (_SEASON) references T_SEASON