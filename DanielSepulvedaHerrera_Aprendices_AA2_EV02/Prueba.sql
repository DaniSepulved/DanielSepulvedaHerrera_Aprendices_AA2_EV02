create database Prueba;
use Prueba;
drop database Prueba;

create table Usuarios(
    nombre varchar(30) not null,
    password varchar(250) not null
);

create table Vehiculos(
    idVehiculo int auto_increment primary key,
    idUsuario int not null,
    foreign key (idUsuario) references Usuarios(idUsuario),
    placa varchar(10) not null unique,
    tipoVehiculo enum('Auto', 'Moto', 'Bus', 'Bicicleta') not null,
    marca varchar(30) not null,
    modelo varchar(30) not null,
    color varchar(30) not null
);

create table EspaciosParqueo(
    idEspacio int auto_increment primary key,
    ubicacion varchar(50) not null,
    numeroEspacio int not null
);

create table Reservas (
    idReserva int auto_increment primary key,
    idUsuario int not null,
    foreign key (idUsuario) references Usuarios(idUsuario),
    idEspacio int not null,
    foreign key (idEspacio) references EspaciosParqueo(idEspacio),
    idTarifa int not null,
    foreign key (idTarifa) references Tarifas(idTarifa),
    fechaReserva date not null,
    horaInicio datetime not null,
    horaFin datetime not null
);

create table Tarifas(
    idTarifa int auto_increment primary key,
    tipoVehiculo enum('Auto', 'Moto', 'Bus', 'Bicicleta') not null,
    costoHora decimal(10,2) not null,
    costoDia decimal(10,2) not null
);

create table Pagos(
    idPago int auto_increment primary key,
    idReserva int not null,
    foreign key (idReserva) references Reservas(idReserva),
    monto decimal(10,2) not null,
    fechaPago date not null,
    metodoPago varchar(50) not null
);

inserT into Usuarios values (
"Juan", "1234"
);

show tables;
select * from Usuarios;
update Usuarios set nombre = "Marcos" where idUsuario = 2;
SELECT * FROM Usuarios WHERE nombre='Juan' AND password='1234';
delete from Usuarios where idUsuario = 2;