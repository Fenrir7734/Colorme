package com.fenrir.colorme.palette.application.port.in.createpalette;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CreatePaletteCommandTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void validationSuccess() {
        // given
        final CreatePaletteCommand command = givenCommand();

        // when
        final Set<ConstraintViolation<CreatePaletteCommand>> violations = validator.validate(command);

        // then
        assertThat(violations).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({
            "12345",
            "1234567",
            "12345J",
            "#123456"
    })
    void validationFailsForInvalidColorCode(String invalidColorCode) {
        // given
        final CreatePaletteCommand command = givenCommand();
        command.getColors().add(invalidColorCode);

        // when
        final Set<ConstraintViolation<CreatePaletteCommand>> violations = validator.validate(command);

        // then
        assertThat(violations).size().isEqualTo(1);

        final ConstraintViolation<CreatePaletteCommand> violation = violations.iterator().next();
        assertThat(violation.getInvalidValue()).isEqualTo(invalidColorCode);
    }

    @Test
    void validationFailsForTooFewColors() {
        // given
        final CreatePaletteCommand command = givenCommand();
        command.getColors().remove(0);
        command.getColors().remove(0);

        // when
        final Set<ConstraintViolation<CreatePaletteCommand>> violations = validator.validate(command);

        // then
        assertThat(violations).size().isEqualTo(1);

        final ConstraintViolation<CreatePaletteCommand> violation = violations.iterator().next();
        assertThat(violation.getInvalidValue()).isEqualTo(command.getColors());
    }

    @Test
    void validationFailsForTooManyColors() {
        // given
        final CreatePaletteCommand command = givenCommand();
        command.setColors(Collections.nCopies(11, "123456"));

        // when
        final Set<ConstraintViolation<CreatePaletteCommand>> violations = validator.validate(command);

        // then
        assertThat(violations).size().isEqualTo(1);

        final ConstraintViolation<CreatePaletteCommand> violation = violations.iterator().next();
        assertThat(violation.getInvalidValue()).isEqualTo(command.getColors());
    }

    @Test
    void validationFailsForNullValueAsColors() {
        // given
        final CreatePaletteCommand command = givenCommand();
        command.setColors(null);

        // when
        final Set<ConstraintViolation<CreatePaletteCommand>> violations = validator.validate(command);

        // then
        assertThat(violations).size().isEqualTo(1);

        final ConstraintViolation<CreatePaletteCommand> violation = violations.iterator().next();
        assertThat(violation.getInvalidValue()).isEqualTo(command.getColors());
    }

    @Test
    void validationFailsForNullValueAsTags() {
        // given
        final CreatePaletteCommand command = givenCommand();
        command.setTags(null);

        // when
        final Set<ConstraintViolation<CreatePaletteCommand>> violations = validator.validate(command);

        // then
        assertThat(violations).size().isEqualTo(1);

        final ConstraintViolation<CreatePaletteCommand> violation = violations.iterator().next();
        assertThat(violation.getInvalidValue()).isEqualTo(command.getTags());
    }

    private CreatePaletteCommand givenCommand() {
        CreatePaletteCommand command = new CreatePaletteCommand();
        command.setColors(new ArrayList<>(Arrays.asList("123456", "ABCDEF", "1A2F3A")));
        command.setTags(new ArrayList<>());
        return command;
    }
}