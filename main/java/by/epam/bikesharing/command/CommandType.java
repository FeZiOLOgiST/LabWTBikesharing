package by.epam.bikesharing.command;

import by.epam.bikesharing.command.admin.AddBikeCommand;
import by.epam.bikesharing.command.admin.AddModelCommand;
import by.epam.bikesharing.command.admin.ChangeCostCommand;
import by.epam.bikesharing.command.admin.ChangeSpotCommand;
import by.epam.bikesharing.command.balance.AddCardCommand;
import by.epam.bikesharing.command.balance.CardsPageCommand;
import by.epam.bikesharing.command.balance.ReplenishCommand;
import by.epam.bikesharing.command.balance.ReplenishPageCommand;
import by.epam.bikesharing.command.login.LoginCommand;
import by.epam.bikesharing.command.login.LogoutCommand;
import by.epam.bikesharing.command.pages.*;
import by.epam.bikesharing.command.profile.LocalizationCommand;
import by.epam.bikesharing.command.profile.ProfileCommand;
import by.epam.bikesharing.command.profile.SaveProfileCommand;
import by.epam.bikesharing.command.rent.FinishRentCommand;
import by.epam.bikesharing.command.rent.RentCommand;
import by.epam.bikesharing.command.signup.RegisterCommand;
import by.epam.bikesharing.command.signup.SignupCommand;
import by.epam.bikesharing.command.signup.VerifyCommand;

public enum CommandType {

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    SIGNUP {
        {
            this.command = new SignupCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    VERIFY {
        {
            this.command = new VerifyCommand();
        }
    },
    PROFILE {
        {
            this.command = new ProfileCommand();
        }
    },
    ADD_CARD {
        {
            this.command = new AddCardCommand();
        }
    },
    CARDS_PAGE {
        {
            this.command = new CardsPageCommand();
        }
    },
    REPLENISH {
        {
            this.command = new ReplenishCommand();
        }
    },
    REPLENISH_PAGE {
        {
            this.command = new ReplenishPageCommand();
        }
    },
    RENT {
        {
            this.command = new RentCommand();
        }
    },
    FINISH_RENT {
        {
            this.command = new FinishRentCommand();
        }
    },
    SAVE_PROFILE {
        {
            this.command = new SaveProfileCommand();
        }
    },
    MAIN_PAGE {
        {
            this.command = new MainPageCommand();
        }
    },
    BIKES_PAGE {
        {
            this.command = new BikesPageCommand();
        }
    },
    USERS_PAGE {
        {
            this.command = new UsersPageCommand();
        }
    },
    RENTS_PAGE {
        {
            this.command = new RentsPageCommand();
        }
    },
    MODELS_PAGE {
        {
            this.command = new ModelsPageCommand();
        }
    },
    ADD_MODEL {
        {
            this.command = new AddModelCommand();
        }
    },
    CHANGE_COST {
        {
            this.command = new ChangeCostCommand();
        }
    },
    ADD_BIKE {
        {
            this.command = new AddBikeCommand();
        }
    },
    CHANGE_SPOT {
        {
            this.command = new ChangeSpotCommand();
        }
    },
    LOCALIZATION {
        {
            this.command = new LocalizationCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}