-- Crear la tabla 'Usuario'
CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    rol VARCHAR(50),
    eliminadoPor INT,
    aceptadoPor INT,
    FOREIGN KEY (eliminadoPor) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (aceptadoPor) REFERENCES Usuario(idUsuario)
);

-- Crear la tabla 'Pelicula'
CREATE TABLE Pelicula (
    idPelicula INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    reparto TEXT,
    anio INT,
    puntuacion DECIMAL(3,2),
    idAceptador INT,
    FOREIGN KEY (idAceptador) REFERENCES Usuario(idUsuario)
);

-- Crear la tabla 'Lista Personalizada'
CREATE TABLE ListaPersonalizada (
    idLista INT AUTO_INCREMENT PRIMARY KEY,
    nombreLista VARCHAR(255) NOT NULL,
    estado VARCHAR(50),
    idUsuario INT,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

-- Crear la tabla 'Resena'
CREATE TABLE Resena (
    idUsuario INT,
    idPelicula INT,
    comentario TEXT,
    puntuacion DECIMAL(3,2),
    PRIMARY KEY (idUsuario, idPelicula),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idPelicula) REFERENCES Pelicula(idPelicula)
);

-- Crear la tabla 'Alquiler'
CREATE TABLE Alquiler (
    idUsuario INT,
    idPelicula INT,
    fecha DATE,
    PRIMARY KEY (idUsuario, idPelicula),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idPelicula) REFERENCES Pelicula(idPelicula)
);

-- Crear la tabla 'Pertenece'
CREATE TABLE Pertenece (
    idPelicula INT,
    idLista INT,
    PRIMARY KEY (idPelicula, idLista),
    FOREIGN KEY (idPelicula) REFERENCES Pelicula(idPelicula),
    FOREIGN KEY (idLista) REFERENCES ListaPersonalizada(idLista)
);
