package com.springbootproject.product.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.assertj.core.api.Assertions.*;

class CategoryAlreadyExistsExceptionTest {

    @Test
    void testCategoryAlreadyExistsExceptionCreation() {
        // Arrange
        String errorMessage = "Category already exists";

        // Act
        CategoryAlreadyExistsException exception = new CategoryAlreadyExistsException(errorMessage);

        // Assert
        assertThat(exception).isNotNull();
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void testCategoryAlreadyExistsExceptionMessage() {
        // Arrange
        String message = "Electronics category already exists";

        // Act
        CategoryAlreadyExistsException exception = new CategoryAlreadyExistsException(message);

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Electronics category already exists");
    }

    @Test
    void testCategoryAlreadyExistsExceptionIsRuntimeException() {
        // Act
        CategoryAlreadyExistsException exception = new CategoryAlreadyExistsException("Test");

        // Assert
        assertThat(exception).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testCategoryAlreadyExistsExceptionThrowable() {
        // Arrange
        String message = "Category test already exists";

        // Act
        CategoryAlreadyExistsException exception = new CategoryAlreadyExistsException(message);

        // Assert
        assertThat(exception).isInstanceOf(Throwable.class);
    }

    @Test
    void testCategoryAlreadyExistsExceptionWithDifferentMessages() {
        // Arrange
        String message1 = "Category 1 exists";
        String message2 = "Category 2 exists";

        // Act
        CategoryAlreadyExistsException exception1 = new CategoryAlreadyExistsException(message1);
        CategoryAlreadyExistsException exception2 = new CategoryAlreadyExistsException(message2);

        // Assert
        assertThat(exception1.getMessage()).isNotEqualTo(exception2.getMessage());
    }

    @Test
    void testCategoryAlreadyExistsExceptionAnnotation() {
        // Act
        Class<CategoryAlreadyExistsException> exceptionClass = CategoryAlreadyExistsException.class;

        // Assert
        assertThat(exceptionClass.isAnnotationPresent(ResponseStatus.class)).isTrue();
        ResponseStatus annotation = exceptionClass.getAnnotation(ResponseStatus.class);
        assertThat(annotation.value()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCategoryAlreadyExistsExceptionWithSpecialCharacters() {
        // Arrange
        String message = "Category 'Electronics & Gadgets' already exists!";

        // Act
        CategoryAlreadyExistsException exception = new CategoryAlreadyExistsException(message);

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Category 'Electronics & Gadgets' already exists!");
    }

    @Test
    void testCategoryAlreadyExistsExceptionWithNumericValue() {
        // Arrange
        String message = "Category with ID 123 already exists";

        // Act
        CategoryAlreadyExistsException exception = new CategoryAlreadyExistsException(message);

        // Assert
        assertThat(exception.getMessage()).contains("123");
    }

    @Test
    void testCategoryAlreadyExistsExceptionCanBeCaught() {
        // Arrange
        String message = "Test exception";

        // Act & Assert
        assertThatThrownBy(() -> {
            throw new CategoryAlreadyExistsException(message);
        }).isInstanceOf(RuntimeException.class)
          .hasMessage(message);
    }

    @Test
    void testCategoryAlreadyExistsExceptionHttpStatusConflict() {
        // Act
        ResponseStatus annotation = CategoryAlreadyExistsException.class.getAnnotation(ResponseStatus.class);

        // Assert
        assertThat(annotation).isNotNull();
        assertThat(annotation.value()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCategoryAlreadyExistsExceptionWithNullMessage() {
        // Act
        CategoryAlreadyExistsException exception = new CategoryAlreadyExistsException(null);

        // Assert
        assertThat(exception.getMessage()).isNull();
    }

    @Test
    void testCategoryAlreadyExistsExceptionWithEmptyString() {
        // Act
        CategoryAlreadyExistsException exception = new CategoryAlreadyExistsException("");

        // Assert
        assertThat(exception.getMessage()).isEmpty();
    }

    @Test
    void testCategoryAlreadyExistsExceptionStackTrace() {
        // Act
        CategoryAlreadyExistsException exception = new CategoryAlreadyExistsException("Test");

        // Assert
        assertThat(exception.getStackTrace()).isNotEmpty();
    }
}

