-- Create table library

-- Table User
CREATE TABLE users (
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    date_birth DATE NOT NULL,
    email NVARCHAR(255) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    roles NVARCHAR(50) NOT NULL DEFAULT 'USER',
    created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    deleted_at DATETIME2,
    active BIT NOT NULL DEFAULT 1
);

-- Table Book
CREATE TABLE book (
    id BIGINT IDENTITY PRIMARY KEY,
    title NVARCHAR(255) NOT NULL,
    author NVARCHAR(255) NOT NULL,
    genre NVARCHAR(255) NOT NULL,
    is_available BIT NOT NULL DEFAULT 1
);

-- Table Reservation
CREATE TABLE reservation (
    id BIGINT IDENTITY PRIMARY KEY,
    user_id INT NOT NULL,
    book_id BIGINT NOT NULL,
    status NVARCHAR(50) NOT NULL DEFAULT 'PENDING',
    updated_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (book_id) REFERENCES Book(id)
);

-- Table RefreshToken
CREATE TABLE refresh_token (
    id BIGINT IDENTITY PRIMARY KEY,
    token NVARCHAR(MAX) NOT NULL,
    username NVARCHAR(255) NOT NULL,
    FOREIGN KEY (username) REFERENCES Users(email)
);
