package com.objavieni.repository;

import com.objavieni.user.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferencesRepository extends JpaRepository<Preferences,Long> {
}
