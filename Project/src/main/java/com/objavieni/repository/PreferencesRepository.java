package com.objavieni.repository;

import com.objavieni.user.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//TODO decide if needed, if not - remove
public interface PreferencesRepository extends JpaRepository<Preferences, UUID> {
}
