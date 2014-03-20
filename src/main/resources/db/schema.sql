create table T_SEASON (
      _UUID varchar(255) not null,
      _CURRENT boolean,
      _NAME varchar(255) not null,
      primary key (_UUID)
);
alter table T_SEASON add constraint UK_qwxec7uhaopnq19bk28a7r658  unique (_NAME);