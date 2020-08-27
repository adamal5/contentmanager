package com.qa.contentmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "Sponsor ID does not exist")
public class SponsorNotFoundException extends EntityNotFoundException {
}