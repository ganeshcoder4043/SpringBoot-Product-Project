package com.springbootproject.product.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.assertj.core.api.Assertions.*;

class CategoryNotFoundExceptionTest {

    @Test
    void testCategoryNotFoundExceptionCreation() {
        // Arrange
        String errorMessage = "Category not found";

        // Act
        CategoryNotFoundException exception = new CategoryNotFoundException(errorMessage);

        // Assert
        assertThat(exception).isNotNull();
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    void testCategoryNotFoundExceptionMessage() {
        // Arrange
        String message = "Category with ID 5 not found";

        // Act
        CategoryNotFoundException exception = new CategoryNotFoundException(message);

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Category with ID 5 not found");
    }

    @Test
    void testCategoryNotFoundExceptionIsRuntimeException() {
        // Act
        CategoryNotFoundException exception = new CategoryNotFoundException("Test");

        // Assert
        assertThat(exception).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testCategoryNotFoundExceptionThrowable() {
        // Act
        CategoryNotFoundException exception = new CategoryNotFoundException("Test");

        // Assert
        assertThat(exception).isInstanceOf(Throwable.class);
    }

    @Test
    void testCategoryNotFoundExceptionWithDifferentMessages() {
        // Arrange
        String message1 = "Category 1 not found";
        String message2 = "Category 2 not found";

        // Act
        CategoryNotFoundException exception1 = new CategoryNotFoundException(message1);
        CategoryNotFoundException exception2 = new CategoryNotFoundException(message2);

        // Assert
        assertThat(exception1.getMessage()).isNotEqualTo(exception2.getMessage());
    }

    @Test
    void testCategoryNotFoundExceptionAnnotation() {
        // Act
        Class<CategoryNotFoundException> exceptionClass = CategoryNotFoundException.class;

        // Assert
        assertThat(exceptionClass.isAnnotationPresent(ResponseStatus.class)).isTrue();
        ResponseStatus annotation = exceptionClass.getAnnotation(ResponseStatus.class);
        assertThat(annotation.value()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testCategoryNotFoundExceptionWithSpecialCharacters() {
        // Arrange
        String message = "Category 'Electronics & Gadgets' not found!";

        // Act
        CategoryNotFoundException exception = new CategoryNotFoundException(message);

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Category 'Electronics & Gadgets' not found!");
    }

    @Test
    void testCategoryNotFoundExceptionWithNumericValue() {
        // Arrange
        String message = "Category with ID 999 not found";

        // Act
        CategoryNotFoundException exception = new CategoryNotFoundException(message);

        // Assert
        assertThat(exception.getMessage()).contains("999");
    }

    @Test
    void testCategoryNotFoundExceptionCanBeCaught() {
        // Arrange
        String message = "Test exception";

        // Act & Assert
        assertThatThrownBy(() -> {
            throw new CategoryNotFoundException(message);
        }).isInstanceOf(RuntimeException.class)
          .hasMessage(message);
    }

    @Test
    void testCategoryNotFoundExceptionHttpStatusNotFound() {
        // Act
        ResponseStatus annotation = CategoryNotFoundException.class.getAnnotation(ResponseStatus.class);

        // Assert
        assertThat(annotation).isNotNull();
        assertThat(annotation.value()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testCategoryNotFoundExceptionWithNullMessage() {
        // Act
        CategoryNotFoundException exception = new CategoryNotFoundException(null);

        // Assert
        assertThat(exception.getMessage()).isNull();
    }

    @Test
    void testCategoryNotFoundExceptionWithEmptyString() {
        // Act
        CategoryNotFoundException exception = new CategoryNotFoundException("");

        // Assert
        assertThat(exception.getMessage()).isEmpty();
    }

    @Test
    void testCategoryNotFoundExceptionStackTrace() {
        // Act
        CategoryNotFoundException exception = new CategoryNotFoundException("Test");

        // Assert
        assertThat(exception.getStackTrace()).isNotEmpty();
    }

    @Test
    void testCategoryNotFoundExceptionWithCategoryId() {
        // Arrange
        Long categoryId = 42L;
        String message = "Category id " + categoryId + " not found";

        // Act
        CategoryNotFoundException exception = new CategoryNotFoundException(message);

        // Assert
        assertThat(exception.getMessage()).contains("42");
    }

    @Test
    void testCategoryNotFoundExceptionDifferentFromAlreadyExists() {
        // Act
        CategoryNotFoundException notFoundEx = new CategoryNotFoundException("Not found");
        CategoryAlreadyExistsException alreadyExistsEx = new CategoryAlreadyExistsException("Already exists");

        // Assert
        assertThat(notFoundEx.getClass()).isNotEqualTo(alreadyExistsEx.getClass());
        assertThat(notFoundEx).isNotInstanceOf(CategoryAlreadyExistsException.class);
    }

    @Test
    void testCategoryNotFoundExceptionWithLongMessage() {
        // Arrange
        String longMessage = "The category with the specified identifier could not be found in the database. " +
                           "Please check the category ID and try again.";

        // Act
        CategoryNotFoundException exception = new CategoryNotFoundException(longMessage);

        // Assert
        assertThat(exception.getMessage()).isEqualTo(longMessage);
    }
}

