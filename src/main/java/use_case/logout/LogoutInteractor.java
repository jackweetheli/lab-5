package use_case.logout;

import data_access.InMemoryUserDataAccessObject;

/**
 * The LogoutInteractor class is responsible for handling the logout use case.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private final InMemoryUserDataAccessObject userRepository;
    private final LogoutOutputBoundary presenter;

    public LogoutInteractor(InMemoryUserDataAccessObject userRepository, LogoutOutputBoundary presenter) {
        this.userRepository = userRepository;
        this.presenter = presenter;
    }

    @Override
    public void execute(LogoutInputData inputData) {
        final String username = inputData.getUsername();
        if (userRepository.getCurrentUsername().equals(username)) {
            userRepository.setCurrentUsername(null);
            final LogoutOutputData outputData = new LogoutOutputData(username);
            presenter.prepareSuccessView(outputData);
        }
        else {
            presenter.prepareFailView("User is not logged in.");
        }
    }
}
