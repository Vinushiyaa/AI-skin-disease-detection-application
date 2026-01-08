package com.skure.app.scan;

import com.skure.app.repository.ImageAnalysisRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class ScanViewModel_Factory implements Factory<ScanViewModel> {
  private final Provider<ImageAnalysisRepository> imageAnalysisRepositoryProvider;

  public ScanViewModel_Factory(Provider<ImageAnalysisRepository> imageAnalysisRepositoryProvider) {
    this.imageAnalysisRepositoryProvider = imageAnalysisRepositoryProvider;
  }

  @Override
  public ScanViewModel get() {
    return newInstance(imageAnalysisRepositoryProvider.get());
  }

  public static ScanViewModel_Factory create(
      Provider<ImageAnalysisRepository> imageAnalysisRepositoryProvider) {
    return new ScanViewModel_Factory(imageAnalysisRepositoryProvider);
  }

  public static ScanViewModel newInstance(ImageAnalysisRepository imageAnalysisRepository) {
    return new ScanViewModel(imageAnalysisRepository);
  }
}
